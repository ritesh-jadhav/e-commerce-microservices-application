package com.boot.external.decoder;

import com.boot.exception.CustomException;
import com.boot.external.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper mapper = new ObjectMapper();


        log.info("message::{}",response.request().url());
        log.info("Message::{}",response.request().headers());
        try {
            ErrorResponse errorResponse = mapper.readValue(response.body().asInputStream(),
                    ErrorResponse.class);
            return new CustomException(errorResponse.getErrorMessage(),
                    errorResponse.getErrorCode(),
                    response.status());

        } catch (IOException e) {
            throw new CustomException("Internal Server error","INTERNAL_SERVER_ERROR",500);
        }
    }
}
