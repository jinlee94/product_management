package kr.co.hanbit.product_management.application;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated // @Valid가 붙은 메서드 매개변수를 유효성 검사하겠다는 의미
public class ValidationService {

    // 유효성 검사 클래스

    // 매개변수 T의 타입은 어떤 타입이든 가능
    public <T> void checkValid(@Valid T validationTarget){
        //do anything
    }
}
