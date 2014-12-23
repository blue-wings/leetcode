package tools;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: FR
 * Time: 12/22/14 5:53 PM
 * 每秒270w的id生成能力
 */
public class UUID {

    private Long HORIZON_MILLI=1419242747142L;
    private final static Integer SEQUENCE_BITS=12;
    private final static Integer WORK_ID_BITS=5;
    private final static Integer DATA_CENTER_BITS=5;
    private final static Integer MILLI_SECOND_BITS=41;
    private final static Integer MAX_SEQUENCE=-1 ^ (-1<<SEQUENCE_BITS);
    private final static Integer MAX_WORK_ID=-1 ^ (-1<<WORK_ID_BITS);
    private final static Integer MAX_DATA_CENTER_ID=-1 ^ (-1 << DATA_CENTER_BITS);
    private final static Long MAX_MILLISECONDS = -1L ^ (-1L << MILLI_SECOND_BITS);

    private final static Integer MILLI_SHIFT= DATA_CENTER_BITS + WORK_ID_BITS + SEQUENCE_BITS;
    private final static Integer DATA_CENTER_SHIFT= WORK_ID_BITS + SEQUENCE_BITS;
    private final static Integer WORK_ID_SHIFT= SEQUENCE_BITS;


    private Integer work_id=1;
    private Integer data_center_id=1;


    private long sequence = 0;
    private long lastTimestamp=0;

    public UUID(Integer workId, Integer dataCenterId) {
        if((workId & MAX_WORK_ID) == 0){
            throw new RuntimeException("work id is too large");
        }
        if((dataCenterId & MAX_DATA_CENTER_ID) == 0){
            throw new RuntimeException("data center id is too large");
        }
        this.work_id = workId;
        this.data_center_id = dataCenterId;
    }

    public synchronized long nextId(){
        long milliseconds = System.currentTimeMillis();
        if(this.lastTimestamp == milliseconds){
            this.sequence = (this.sequence+1) & MAX_SEQUENCE;
            if(this.sequence == 0){
                milliseconds = tilNextMillis(this.lastTimestamp);
            }
        }else if(milliseconds < this.lastTimestamp){
            throw new RuntimeException("clock wrong");
        }else {
            this.sequence = 0;
        }
        this.lastTimestamp = milliseconds;
        if(((milliseconds-HORIZON_MILLI) & MAX_MILLISECONDS) == 0){
            throw new RuntimeException("time is too large");
        }
        long nextId = ((milliseconds-HORIZON_MILLI)<<MILLI_SHIFT)+(this.data_center_id<<DATA_CENTER_SHIFT)+(this.work_id<<WORK_ID_SHIFT)+this.sequence;
        return nextId;
    }

    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    public static void main(String[] args) throws InterruptedException {
        final AtomicInteger counter = new AtomicInteger(0);
        final ConcurrentHashMap set = new ConcurrentHashMap();
        final UUID uuid = new UUID(31, 1);
        final CountDownLatch countDownLatch = new CountDownLatch(100);
        final long start = System.currentTimeMillis();
        for (int i=0; i<100; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while ((System.currentTimeMillis() - start)<6000){
                        long id = uuid.nextId();
                        set.put(id, "");
                        counter.incrementAndGet();
                    }
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("use time " + (end - start));
        System.out.println(counter.get() + "");
        System.out.println(set.keySet().size());
    }

    public static void main1(String[] args){
        System.out.println(Long.toBinaryString(MAX_MILLISECONDS));
        long start = System.currentTimeMillis();
        UUID uuid = new UUID(31, 1);
        Set<Long> set = new HashSet<Long>();
        while ((System.currentTimeMillis() - start)<6000){
            long id = uuid.nextId();
            set.add(id);
        }
        System.out.println(set.size());
    }
}
