package util;

import java.util.Random;

public class Token {
    Random rand=new Random();
    public String generateToken(int id)
    {
        String token=""+rand.nextInt(1,10)+(id/2)+rand.nextInt(1,10);
        return token;
    }
    
}
