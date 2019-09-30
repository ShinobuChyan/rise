package github.shinobu.rise.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Shinobu
 * @since 8/23/2019
 */
public class C extends AbstractC {
    @Override
    public void m1() {
        System.out.println();
    }

    static class Model {
        List<Module> moduleList;
    }

    static class Module {
        Input input;
    }

    static class Input {
        List<String> list;
    }
}
