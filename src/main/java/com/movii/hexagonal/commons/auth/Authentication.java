package com.movii.hexagonal.commons.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movii.hexagonal.commons.UtilHelper;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@Getter
public class Authentication implements IAuthentication {

    private String key = "MY_COMPONENT_SECRET_KEY";
    private String nonce;
    private String seed;

    /**
     * Invoke this function each time that you use this class to generate a new signature
     * @return AuthOut
     */
    public AuthOut generateAuth() {
        this.generate();
        String tranKey = this.getTranKey(this.getNonce(), this.getSeed(), this.getKey());
        return new AuthOut(this.getNonce64(), this.getSeed(), tranKey);
    }

    /**
     * Invoke this function each time that you use this class to validate signature
     * @param headers
     * @return boolean
     * @throws AuthenticationException
     */
    public boolean signatureValidation(final Map<String, String> headers) throws AuthenticationException {

        final ObjectMapper mapper = new ObjectMapper();
        final AuthIn authIn = mapper.convertValue(headers, AuthIn.class);

        try {
            Date seedIn = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ").parse(authIn.getSeed());
            long diff  = TimeUnit.MILLISECONDS.toMinutes((new Date().getTime() - seedIn.getTime()));

            if(diff > 5 || diff < 0){
                throw new AuthenticationException("La diferencia de la fecha de Seed mayor de 5 minutos");
            }
        } catch (ParseException e) {
            throw new AuthenticationException("El formato de la fecha de Seed es incorrecto");
        }

        String nonceIn = UtilHelper.base64ToString(authIn.getNonce());
        String newTranKey = this.getTranKey(nonceIn, authIn.getSeed(), this.getKey());

        if(!authIn.getTrankey().equals(newTranKey)) {
            throw new AuthenticationException("El hash de Trankey no coincide");
        }
        return true;
    }

    /**
     * Construct the nonce and seed to generate signature
     */
    private void generate() {
        this.nonce = new BigInteger(130, new SecureRandom()).toString();
        this.seed = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ", Locale.getDefault())).format(new Date()); //Date ISO 8601
    }

    /**
     * Constructs the signature digest String to use on the input authentication structure
     * @param nonce
     * @param seed
     * @param key
     * @return String
     */
    private String getTranKey(String nonce, String seed, String key) {
        try {
            return UtilHelper.base64(UtilHelper.sha1(UtilHelper.md5(nonce +"~"+ seed +"~"+ key)));
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     * Returns the Base64 encoded nonce used to generate the signature digest
     * @return String
     */
    private String getNonce64() {
        return UtilHelper.base64(this.nonce.getBytes());
    }
}
