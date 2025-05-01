package kr.or.ddit.functionalinterface;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

/**
 * Optional : 조건부 실행 코드를 표현할 때 사용되고, if~else 조건문의 대체
 * Stream : 반복문의 대체, 일련의 연속성을 가진 집합 요소들에 대해 반복적인 처리를 표현할 때
 *  -- 작업을 표현할때 FunctionalInterface 형태로 표현함
 *  functionalInterface ? 자바에서 일급객체(일급함수)의 표현 -> Lambda문법으로 표현 가능.
 *  1. 인자를 받고 반환값이 없음. : Consumer
 *  2. 인자를 받고 새로운 값을 반환. : Function
 *  3. 인자를 받고, 여부를 확인한 후 boolean 반환 : Predicate
 *  4. 인자는 없고, 새로운 객체를 반환 : Supplier
 *  
 *  일급함수란?
 *  	1. 객체 없이 독립적으로 존재하는 함수
 *  	function name() {}, obj = {name : function() {}}
 *  	name() -> 일급함수		obj.name() -> 메서드
 *  	2. 함수의 래퍼런스를 변수로 할당받을 수 있음
 *  	const fnName = () => {}, const fnName = function() {}
 *  	3. 함수를 또다른 함수의 인자로 전달할 수 있음
 *  	function outer(callback) {
 *  		...
 *  		callback();
 *  	}
 *  	outer(fnName);
 *  	4. 함수를 반환하는 함수가 존재할 수 있음
 *  	function fnRet() {
 *  		return () => {}
 *  	}
 *  	const fnInner = fnRet();
 */
class LambdaAndOptionalAndStream {
	@Test
	void test3() {
		List<String> list = List.of("a1","b1","b2","c30");
		String collected = list.stream()
			.map(e1->e1.substring(1))
//			.map(Integer::parseInt)
//			.filter(e1->e1>=30)
			.collect(Collectors.joining(","));
//			.forEach(e1->System.out.println(e1));
			System.out.println(collected);
	}
	
	@Test
	void test2() {
		Object target = null;
		if(target != null) {
			System.out.println(target.toString());
		} else {
			System.out.println("객체없음");
		}
		
		Optional
			.ofNullable(target)
			.map(Object::toString)
			.ifPresent(System.out::println);
		
	}

	@Test
	void test() {
		FnTest oldFashion = new FnTest() {
			
			@Override
			public void print(Object arg) {
				System.out.println("옛날방식으로 그냥 객체지향적으로 만듦" + arg);
			}
		};
		oldFashion.print("전달값");
		
		FnTest newFashion = arg -> System.out.println("람다로 만든 객체" + arg);
		newFashion.print("전달값");
	}

}
