package kr.co.hanbit.product_management;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductManagementApplication.class, args);
    }

    /*
    modelmapper를 매번 new 키워드로 생성할 수도 있지만, 미리 빈으로 등록한 후 의존성을 주입 받아서 사용하는 것이 성능상 유리하다.
    기본 설정은 '매개 변수가 없는 생성자로 인스턴스를 생성한 후 setter로 값을 초기화하여 변환하는 것'이다.
    setter 없이도 엔티티와 DTO를 변환 가능하도록 하려면 다음과 같은 설정으로 빈을 생성해야 한다.
    이 설정은 ModelMapper가 private인 필드에 리플렉션 API(자바에서 제공함)로 접근하여 변환할 수 있도록 만들어준다.
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);
        return modelMapper;
    }

}
