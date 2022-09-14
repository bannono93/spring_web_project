package com.zerock.ex2.repository;

import com.zerock.ex2.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    // 메소드이름(findByMnBetweenOrderByMnoDesc)을
    // 보면 mno를 기준으로해서 between 구문을 사용하고
    // order by가 적용될 것임을 알 수 있다.
    List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);

    //Pageable 파라미터를 같이 결합해서 사용할 수 있기 때문에
    //정렬에 관련 분분은 Pageable로 처리해서 좀 거 간략한 메서드를 생성 할 수 있다.
    Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable);
}
