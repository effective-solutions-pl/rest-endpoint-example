package pl.effectivesolutions.restendpointexample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Example controller.
 * Created by Tomasz Sokół on 2020-03-25.
 */
@RestController
public class ExampleController {

    @GetMapping("/numbers")
    public List<Integer> numbers(@RequestParam(name = "start") Integer start) {
        Objects.nonNull(start);
        List<Integer> res = new ArrayList<>();
        for (Integer i = 0; i <= start; i++) {
            res.add(i);
        }
        return res;
    }
}
