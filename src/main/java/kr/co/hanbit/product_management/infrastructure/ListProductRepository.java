package kr.co.hanbit.product_management.infrastructure;

import kr.co.hanbit.product_management.domain.EntityNotFoundException;
import kr.co.hanbit.product_management.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ListProductRepository {


    private List<Product> products = new CopyOnWriteArrayList<Product>();
    /* CopyOnWriteArrayList를 사용한 이유 : web app이 멀티스레드라는 특수환경 때문에 '스레드 세이프'한 컬렉션을 사용하기 때문.
    ArrayList는 스레드 세이프하지 않은, 즉 스레드 안정성이 없는 컬렉션이다.
    물론 지역변수나 매개 변수로 전달되는 리스트의 경우 보통 하나의 스레드에서만 접근하기 때문에
    스레드 안정성이 필요하지 않아 ArrayList를 많이 사용한다.
     */

    private AtomicLong sequence = new AtomicLong(1L);
    // AtomicLong은 스레드 안정성을 가지는 클래스로, Long 타입의 값을 안전하게 다룰 수 있도록 만든다.

    public Product add(Product product) {
        product.setId(sequence.getAndAdd(1L)); // 값을 가져와서 1씩 증가시키는 메소드
        products.add(product);
        return product;
    }

    public Product findById(Long id){
        // 필터의 결과가 참(True)인 결과만 뽑아내는 코드
        return products.stream()
                .filter(product -> product.sameId(id))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("해당 id의 Product를 찾지 못했습니다. 왜 예외 안 던져")); // Optional 객체에서 Product 객체로 변환할 때 비어있으면 예외를 던지는 메소드를 선택했음.
    }

    public List<Product> findAll(){
        return products;
    }

    public List<Product> findByNameContaining(String name){
        return products.stream()
                .filter(product -> product.containsName(name))
                .toList();
    }

    public Product update(Product product) {
        Integer indexToModify = products.indexOf(product);
        products.set(indexToModify, product);
        return product;
    }

    public void delete(Long id) {
        Product product = this.findById(id);
        products.remove(product);
    }
}
