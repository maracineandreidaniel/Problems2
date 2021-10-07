package com.company;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalProblems {

    //todo:pb226
    public static void OptionalApproaching() {
        Optional<Caine> optional = Optional.empty();
        //deoarece optional este un container, acesta trebuie initializat cu empty, nu cu null
    }

    //todo:pb227
    public static void optionalFetching() {
        Optional<Caine> opt = Optional.of(new Caine("emi", 56));
        if (opt.isPresent())
            System.out.println(opt.get());

        //facem asta deoarece cel mai bine este sa verificam prima data daca optionalul contine ceva
        //pentru a nu ne returna o exceptie
    }

    //todo:pb228
    public static void alreadyOptional() {
        Optional<String> optional = Optional.of("CAine");
        optional.orElse("Caine1");
    }

    //todo:pb229
    public String generareCaine() {
        return "Caine";
    }

    public String existentNonDefault() {
        Optional<String> optional = Optional.of("Pisica");
        return optional.orElseGet(this::generareCaine);
    }

    //todo:pb230
    public String throwExcept() {
        Optional<String> optional = Optional.of("Pisica");
        return optional.orElseThrow();
    }

    //todo:pb231
    public void optionalNull() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Optional<Caine> caine=Optional.of(new Caine("abi",4));
       Method method= Method.class.getDeclaredMethod(caine.get().getNume());
       method.invoke(caine.orElse(null));
    }

    //todo:pb232
    public static void consumeOptional(){
        Optional<String> optional=Optional.of("Cal");
        optional.ifPresentOrElse(System.out::println,()-> System.out.println("cal not found"));
    }

    //todo:pb233
    public static Optional<Caine> returnPresentOptional(){
        Optional<Caine> optional=Optional.of(new Caine("Bobita",45));
        return optional.or(()->Optional.of(new Caine("azorica",4)));
    }

    //todo:pb234
    public static String chainingLambdas(int age){
        Caine caine=new Caine("rex",1);
        Caine caine2=new Caine("adi",1);
        List<Caine> lista=new ArrayList<>();
        lista.add(caine);
        lista.add(caine2);
        return lista.stream().filter(c->c.getVarsta()<age).findFirst().map(Caine::getNume).orElse("Nu sunt astfel de caini");
    }

    //todo:pb235
    public static String dontUse(){
        String str="Catel";
        String str2="CatelRezerva";
        //in loc sa facem asta
            return Optional.ofNullable(str).orElse(str2);
        //putem face asta
            //return str==null?str:str2;
    }

}
