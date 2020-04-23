package util;
import java.util.UUID;

import org.junit.Test;

public class UUIDTest {
	
	@Test
	public void GenerateUUID(){
		UUID uuid = UUID.randomUUID();
		String id = uuid.toString().replace("-", "").toUpperCase();
		System.out.println(id);
		
	}

}
