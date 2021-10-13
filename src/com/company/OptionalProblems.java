package com.company;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

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
        Optional<Caine> caine = Optional.of(new Caine("abi", 4));
        Method method = Method.class.getDeclaredMethod(caine.get().getNume());
        method.invoke(caine.orElse(null));
    }

    //todo:pb232
    public static void consumeOptional() {
        Optional<String> optional = Optional.of("Cal");
        optional.ifPresentOrElse(System.out::println, () -> System.out.println("cal not found"));
    }

    //todo:pb233
    public static Optional<Caine> returnPresentOptional() {
        Optional<Caine> optional = Optional.of(new Caine("Bobita", 45));
        return optional.or(() -> Optional.of(new Caine("azorica", 4)));
    }

    //todo:pb234
    public static String chainingLambdas(int age) {
        Caine caine = new Caine("rex", 1);
        Caine caine2 = new Caine("adi", 1);
        List<Caine> lista = new ArrayList<>();
        lista.add(caine);
        lista.add(caine2);
        return lista.stream().filter(c -> c.getVarsta() < age).findFirst().map(Caine::getNume).orElse("Nu sunt astfel de caini");
    }

    //todo:pb235
    public static String dontUse() {
        String str = "Catel";
        String str2 = "CatelRezerva";
        //in loc sa facem asta
        return Optional.ofNullable(str).orElse(str2);
        //putem face asta
        //return str==null?str:str2;
    }

    //todo:pb236
    //de evitat sa facem un field Optional in clasa noastra, ca de exemplu Optional<String> blana din clasa Caine
    //in locul acestuia putem folosi doar un simplu String


    //todo:pb237
    //de evitat sa facem gettere si settere pentru un field Optional
    //putem sa avem un simplu field String si getterul sa returneze Optional.ofNullable(blana)

    //todo:pb238
    //de evitat sa facem settere si sa dam un Optional<String> ca parametru
    //putem avea un simplu field String si setterul sa ia ca parametru un String

    //todo:239
    //de evitat sa dam un Optional<String> ca parametru al unei functii
    //putem da direct un String

    //todo:240
    //de evitat sa ne folosim de Optional<ArrayList<String>>
    //putem sa ne folosim direct de ArrayList<String> (metoda caini())

    //todo:241

    //de evitat sa ne folosim de colectii cu Optional
    //putem sa dam direct Colectie<String>
    public static void collectionOptional() {
        ArrayList<Optional<String>> strings = new ArrayList<>();
        strings.add(Optional.of("caine"));
        strings.add(Optional.of("pisica"));
        System.out.println(strings);
    }

    //todo:242
    public static void nullNotNull() {
        //in cazul in care poate sa fie null
        Optional<String> nulll = Optional.ofNullable("null");
        //in cazul in care nu poate sa fie null
        Optional<String> null2 = Optional.of("null");
    }

    //todo:243
    public static void optionalTypes() {
        //putem avea in loc de
        Optional<Double> optional = Optional.of(12.3);
        //putem avea
        OptionalDouble optionalDouble = OptionalDouble.of(12.3);
    }

    //todo:pb244
    //cand testam, este indicat sa folosim assertEquals direct pe Optionale, nu pentru continutul acestora




}