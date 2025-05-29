package concurrency.demo.service;

import concurrency.demo.repository.CustomizeTableRepository;
import concurrency.demo.repository.CustomizeTimeBoxRepository;
import concurrency.demo.repository.MemberRepository;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(DatabaseCleaner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public abstract class ServiceTest {

    private static final Logger log = LoggerFactory.getLogger(ServiceTest.class);

    @Autowired
    protected MemberRepository memberRepository;

    @Autowired
    protected CustomizeTableRepository customizeTableRepository;

    @Autowired
    protected CustomizeTimeBoxRepository customizeTimeBoxRepository;


    protected void runAtSameTime(int count, Runnable task) throws InterruptedException {
        List<Thread> threads = IntStream.range(0, count)
                .mapToObj(i -> new Thread(task))
                .toList();

        long startTime = System.currentTimeMillis();
        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }
        long endTime = System.currentTimeMillis();
        log.info("All jobs(threads) finished in {} ms", endTime - startTime);
    }
}

