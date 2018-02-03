public class Test {

	private Integer num;
	
	@org.junit.Test
	public  void  test() {
		Test t=new Test();
		t.changeNum(1);
		
		System.out.println(t.num);
	}
	
	public void changeNum(Integer num) {
		this.num=num;
	}
	
	
}
