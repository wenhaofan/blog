package menuTree;

import org.junit.Test;

import com.wenhaofan._admin.diy.html.DiyAdminService;

public class TestService {

	@Test
	public void testViewTree() {
		Runtime r = Runtime.getRuntime();  
		r.gc();  
		long startMem = r.freeMemory(); // 开始时的剩余内存  
		
		
		DiyAdminService service=new DiyAdminService();
		long orz = startMem - r.freeMemory();
		System.out.println(service.viewTree);
	}
}
