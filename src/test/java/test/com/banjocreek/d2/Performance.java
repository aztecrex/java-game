package test.com.banjocreek.d2;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Supplier;

import org.joml.Vector2d;

import com.banjocreek.d2.ImmutableVec2;
import com.banjocreek.d2.MutableVec2;
import com.banjocreek.d2.Vec2;

public final class Performance {

    public static final long iterations = 100000000l;
    
    public static Vec2 immutable() {
        ImmutableVec2 v = new ImmutableVec2(1, 1);
        ImmutableVec2 increment = new ImmutableVec2(-.001,.001);
        for( long i=0; i < iterations; ++i) {
            v = v.plus(increment);
        }
        return v;
    }

    public static Vector2d mutable() {
        Vector2d v = new Vector2d(1, 1);
        Vector2d increment = new Vector2d(-.001,.001);
        for( long i=0; i < iterations; ++i) {
            v.add(increment);
        }
        return v;
        
    }
    
    public static MutableVec2 mutable2() {
        MutableVec2 v = new MutableVec2(1, 1);
        MutableVec2 increment = new MutableVec2(-.001,.001);
        for( long i=0; i < iterations; ++i) {
            v.plus(increment);
        }
        return v;
        
    }

    private static <R> Duration profile (String tag, Supplier<R> f) {
        
        Instant start = Instant.now();
        R result = f.get();
        Instant end = Instant.now();
        Duration profile = Duration.between(start, end);
        System.out.println(tag + ": " + result + " --> " + profile);
        return profile;
    }
    

    public static void main(String[] args) throws Exception {
        mutable2();    // warm it up
        Duration mp2 = profile("mutable2",Performance::mutable2);

        mutable();      // warm it up
        Duration mp = profile("mutable", Performance::mutable);
        

        immutable(); // warm it up
        Duration ip = profile("immutable", Performance::immutable);
        
        float compare = (float)ip.toMillis() / (float)mp.toMillis();
        
        System.out.println("iduration/mduration: " + compare);
        
        System.out.println("mutable ops/s: " + ((double)iterations/(double)mp.toMillis()));
        System.out.println("mutable2 ops/s: " + ((double)iterations/(double)mp2.toMillis()));
        System.out.println("immutable ops/s: " + ((double)iterations/(double)ip.toMillis()));
        
        System.out.println("mutable ops/f: " + ((double)iterations/(double)mp.toMillis()/50d));
        System.out.println("mutable2 ops/f: " + ((double)iterations/(double)mp2.toMillis()/50d));
        System.out.println("immutable ops/f: " + ((double)iterations/(double)ip.toMillis()/50d));

    
    }
   
}
