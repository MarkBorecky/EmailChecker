package org.example;

import java.util.function.Function;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;
import static org.example.Case.match;
import static org.example.Case.mcase;
import static org.example.Result.failure;
import static org.example.Result.success;

public class EmailValidator {

    static Pattern emailPattern = Pattern.compile("^[a-z\\d._%+-]+@[a-z\\d.-]+\\[a-z]{2,4}$");
    static Effect<String> success = s -> System.out.format("Komunikat błędu umieszony w dzienniku zdarzeń: %s\n", s);
    static Effect<String> failure = s -> System.out.format("E-mail weryfikacyjny wysłany do %s\n", s);

    public static void main(String[] args) {
        emailChecker.apply("to.jest@jakis.adress").bind(success, failure);
        emailChecker.apply(null).bind(success, failure);
        emailChecker.apply("").bind(success, failure);
        emailChecker.apply("jan.kowalski@text.com").bind(success, failure);
    }

    static Function<String, Result<String>> emailChecker = s -> match(
            mcase(() -> success(s)),
            mcase(() -> isNull(s), () -> failure("Adress e-mail nie może być wartością null.")),
            mcase(() -> s.length() == 0, () -> failure("Adress e-mail nie może być pusty.")),
            mcase(() -> !emailPattern.matcher(s).matches(), () -> failure("Adress e-mail %s jest niepoprawny".formatted(s)))
    );
}