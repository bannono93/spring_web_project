package com.zerock.ex2.repository;

import com.zerock.ex2.entity.Memo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoReositoryTests {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass() {
        System.out.println(memoRepository.getClass().getName());
    }

    @Test
    public void testInsertDummies() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder().memoText("Sample.." + i).build();
            memoRepository.save(memo);
        });
    }

    @Transactional
    @Test
    public void testSelect() {

        //데이터베이스에 존재하는 mno
        Long mno = 100L;

        Optional<Memo> result = memoRepository.findById(mno);

        System.out.println("========================================");

        if (result.isPresent()) {
            Memo memo = result.get();
            System.out.println(memo);
        }
    }

    @Test
    public void testUpdate() {
        //객체가 있는지 먼저 select 한 후, @Id를 가진 엔티티 객체가 있다면 update
        //그렇지 않으면 insert를 실행한다.
        Memo memo = Memo.builder().mno(100L).memoText("update Text").build();
        System.out.println("========================================");
        System.out.println(memoRepository.save(memo));
    }

    @Test
    public void testDelete() {
        Long mno = 103L;
        memoRepository.deleteById(mno);
        System.out.println("========================================");
    }

    //페이징 처리
    @Test
    public void testPageDefault() {

        //1페이지 ~ 10페이지
        //import 시에 org.springframework.data 관련 클래스들을 사용할것 (중요)
        Pageable pageable = PageRequest.of(0, 10);

        Page<Memo> result = memoRepository.findAll(pageable);

        System.out.println("========================================");

        System.out.println(result);

        //Page 1 of 11 containing com.zerock.ex2.entity.Memo instances

        System.out.println("========================================");

        System.out.println("Total Pages: " + result.getTotalPages()); // 총 몇 페이지

        System.out.println("Total Count: " + result.getTotalElements()); //전체 페이지 번호 0부터 시작

        System.out.println("Page Number: " + result.getNumber()); //현재 페이지 번호

        System.out.println("Page Size: " + result.getSize()); //페이지당 데이터 개수

        System.out.println("has next page? : " + result.hasNext()); //다음페이지 존재 여부

        System.out.println("first page? : " + result.isFirst()); //시작페이지(0) 여부

        System.out.println("========================================");

        for (Memo memo : result.getContent()) {
            System.out.println(memo);
        }
    }

    //정렬 처리
    @Test
    public void testSort() {

        Sort sort1 = Sort.by("mno").descending();

        Sort sort2 = Sort.by("memoText").ascending();

        Sort sortAll = sort1.and(sort2); //정렬 합치기

        Pageable pageable = PageRequest.of(0, 10, sortAll); // 합친 정렬 조건 사용

        Page<Memo> result = memoRepository.findAll(pageable);

        System.out.println("========================================");

        result.get().forEach(memo -> {
            System.out.println(memo);
        });
    }

    //조회조건
    @Test
    public void testQueryMethods() {

        //mno기준 70~80사이 조회
        List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(70L,80L);

        //위 조건에 맞게 목록 출력
        for(Memo memo : list){
            System.out.println(memo);
        }
    }

    //
}