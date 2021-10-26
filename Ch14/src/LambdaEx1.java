
	/* ========================================================================
	 *  Java의 정석 3판 p.799 예제 14-1 : 람다식과 함수형 인터페이스
	 * ========================================================================
	 *
	 *     1. 람다식
	 *      메서드를 하나의 식으로 표현한 것
	 *       예) int sum(int a, int b) {return a+b};
	 *        : (int a, int b) -> a+b
	 *        
	 *      람다식을 통해, 별도의 클래스에 종속하지 않은 익명 메서드를 간단히 작성할 수 있다.
	 *        
	 * ========================================================================
	 *     
	 *     2. 람다식 작성하기
	 *     
	 *      1) 메서드에서 이름과 반환 타입 제거하기
	 *       반환 타입 메서드 이름 (매개변수 선언) { 문장들 ... }
	 *       : (매개변수 선언) -> {문장들}
	 *      
	 *      2) 매개변수의 타입 추론이 가능한 경우 매개변수 선언을 생략할 수 있다.
	 *       예) (int a, int b) -> a+b
	 *       : (a,b) -> a+b
	 *      
	 *      3) 선언된 매개변수가 하나인 경우 매개변수를 생략할 수 있다.
	 *       예) (int a) -> a*a
	 *         : a -> a*a
	 *         
	 *      
	 *      4) 괄호 {} 안의 문장이 하나일 때는 괄호를 생략할 수 있다.
	 *         이 때 끝의 세미콜론은 제거해야함
	 *         또, {} 안의 문장이 return문일 경우 괄호를 생략할 수 없다.
	 *         
	 *       예> (String name, int i) -> {System.out.println(name+"="+i);}
	 *        : (String name, int i) -> System.out.printlnt(name+"="+i)
	 *      
	 *       예> (int a, int b) -> {return a > b ? a : b;} // OK
	 *           (a, b) -> return a>b ? a:b // No
	 *           
	 *      5) 변환값이 있을 경우 return 문 대신, 식으로 대신할 수 있다.
	 *         식의 연산결과 그 자체가 return 값이 된다.
	 *         
	 *        예) (int a, int b) -> {return a>b? a:b;}
	 *           : (int a, int b) -> a>b? a:b
	 *           
	 * ========================================================================
	 *       
	 *     3. 익명 클래스
	 *      이름이 없는 클래스. 클래스의 선언과 객체의 생성을 동시에 할 수 있다.
	 *      
	 *      new 조상클래스의 이름() {
	 *        // 멤버 선언...
	 *      }
	 *      또는
	 *      new 구현인터페이스 이름() {
	 *        // 멤버 선언
	 *      }
	 *      
	 *      1) 구현하고자 하는 인터페이스의 이름을 사용하기 때문에 둘 이상의 인터페이스를 구현할 수 없다.
	 *      2) 이름이 없기 때문에 외부 클래스명$숫자.class 파일로 컴파일 된다.
	 *      
	 *      예>
	 *      interface MyInterface { // 인터페이스
	 *        int max(int a, int b);
	 *      }
	 *      
	 *      MyInterface mi = new MyInterface() { // 인터페이스를 구현한 익명 클래스 
	 *         @Override
	 *         int max(int a, int b) {
	 *           return a > b ? a : b
	 *         }
	 *      };
	 *      int max = mi.max(2,3); //  익명 클래스의 메서드를 호출하여 int 타입의 값을 반환함
	 *      System.out.println(max);
	 *      
	 *      
	 * ========================================================================
	 *     
	 *     4. 함수형 인터페이스 (람다식을 참조변수로 다루기)
	 *     
	 *      1) 람다식은 익명클래스의 객체와 구조적으로 동일하다.
	 *      
	 *      2) 람다식을 익명클래스의 객체로 취급하기 위해, 함수형 인터페이스를 정의.
	 *         인터페이스를 통하여 람다식을 취급한다.
	 *      
	 *         - 하나의 함수형 인터페이스에는 하나의 메서드만 작성
	 *         - 함수형 인터페이스 앞에 @FunctionalInterface 애너테이션을 작성하자.
	 *      
	 *      @FunctionalInterface
	 *      interface MyFunction { // 함수형 인터페이스
	 *       int max(int a, int b);
	 *      }
	 *      
	 *      MyFunction mf = (a,b) -> a>b? a:b; // 람다식(익명객체)으로 함수형 인터페이스 구현
	 *      int max = mf.max(2,3);
	 *      System.out.println(max);
	 *      
	 * ========================================================================
	 *      
	 *     5. 함수형 인터페이스 타입의 매개변수
	 *     
	 *      어떤 메서드의 매개변수가 함수형 인터페이스 타입이면, 이 메서드를 호출할 때
	 *      
	 *      1) 람다식을 참조하는 참조변수를 매개변수로 지정
	 *      2) 참조변수 없이 람다식 자체를 매개변수로 지정
	 *      
	 *      하면 된다.
	 *      
	 *      예>
	 *      
	 *      @FunctionalInterface
	 *      interface MyFunction {
	 *         void mymethod();
	 *      }
	 *      
	 *      ...
	 *      
	 *      void method_a (MyFunction f) {
	 *         f.mymethod();
	 *      }
	 *      ...
	 *      
	 *      MyFunction f = () -> System.out.println("냐옹");
	 *      method_a(f);
	 *      
	 *      또는
	 *      
	 *      method_a(() -> System.out.println("왈왈"));
	 *      
	 * ========================================================================
	 *     
	 *     6. 함수형 인터페이스 타입의 반환타입
	 *     
	 *     어떤 메서드의 반환타입을 함수형 인터페이스로 지정
	 *     -> 함수형 인터페이스의 추상메서드와 동등한 람다식, 혹은 람다식을 가리키는 참조변수를 반환할 수 있음
	 *     
	 *     의의
	 *     1. 메서드를 통하여, 람다식을 주고 받을 수 있다.
	 *     2. 메서드를 변수처럼 취급하여 주고받을 수 있다.
	 *     
	 *     MyFunction emptyFunction() {
	 *        return () -> ();
	 *     }
	 *     
	 * ========================================================================
	 */


@FunctionalInterface
interface MyFunctionEx1_1 {void run();}

public class LambdaEx1 {
	
	static void execute(MyFunctionEx1_1 f) { // execute : 실행하다.
		// MyFuctionEx1_1 타입의 참조변수를 매개변수로 사용할 수도 있고,
		// 직접 () 안에서 람다식을 작성하여 MyFunctionEx1_1을 구현하여 실행할 수도 있다.
		f.run();
		return;
	}
	
	static MyFunctionEx1_1 getMyFunction() {
		// MyFunctionEx1_1 인터페이스를 구현하여 반환 
		MyFunctionEx1_1 f = () -> System.out.println("f3.run()");
		return f;
	}
	
	public static void main(String[] args) {
		MyFunctionEx1_1 f1 = () -> System.out.println("f1.run()");
		// 람다식으로 직접 함수형 인터페이스 MyFunctionEx1_1 구현
		
		MyFunctionEx1_1 f2 = new MyFunctionEx1_1() { // 익명 클래스 선언을 통해, MyFunctionEx1_1 구현
			public void run() {
				System.out.println("f2.run()");
			}
		};
		
		MyFunctionEx1_1 f3 = getMyFunction(); // 메서드를 통해 MyFunctionEx1_1 구현체를 반환받아, 참조
		
		f1.run();
		f2.run();
		f3.run();
		execute(f1); // 함수형 인터페이스의 구현체를 매개변수로 사용
		execute(() -> System.out.println("run()")); // 람다식을 통해 함수형 인터페이스를 구현하고, 이를 그대로 매개변수로 사용
	}

}
