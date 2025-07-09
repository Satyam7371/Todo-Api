package org.example.todoapispring;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("fakeTodoService")
public class FakeTodoService implements TodoService {

    @TimeMonitor
    public String doSomething(){

        for(long i = 1;i<1000000000;i++) {}

        return "Something";
    }
}
