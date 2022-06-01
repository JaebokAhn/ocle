package com.yeol.ocle.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Service
public class SecurityService {
    private static final String SECRET_KEY = "asdfasdfasdfasdfweasdfasdfasdasdfasdfasdfasdf";

    //로그인 성공 시 토큰 생성하여 client로 전달
    public String createToken(String subject, long expTime) {
        if(expTime <= 0) {
            throw new RuntimeException("만료시간이 0보다 커야함.");
        }

        //암호화 알고리즘
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //byte 단위의 secret key를 만들어 줘야 함.
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);

        //signing 된 key 생성
        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());

        //subject : id로 생성
        return Jwts.builder()
                .setSubject(subject)
                .signWith(signingKey, signatureAlgorithm)
                .setExpiration(new Date(System.currentTimeMillis() + expTime))
                .compact();
    }


    //[TODO] 토큰 검증 -> boolean으로 return 하도록 수정
    public String getSubject(String token) {
        //claims : payload 정보
        Claims claims = Jwts.parserBuilder()
                //토큰생성 시 사용한 방법과 동일한 방법으로 signingKey 생성
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
