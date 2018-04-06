import java.text.MessageFormat;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BeerSong {

    public String lyrics() {
        return verses(99,0);
    }

    public String verses(int start, int end) {
        final StringBuilder result = new StringBuilder();
        IntStream.iterate(start, i -> i - 1).limit(end)
                .mapToObj(this::verse).collect(Collectors.toList()).forEach(result::append);
        return result.toString();
    }

    public String verse(int current) {
        if (current == 0)
            return  "No more bottles of beer on the wall, no more bottles of beer.\n" +
                    "Go to the store and buy some more, 99 bottles of beer on the wall.\n\n";
        final int remaining = current - 1;
        return MessageFormat.format("{0} of beer on the wall, {0} of beer.\n" +
                        "Take {1} down and pass it around, {2} of beer on the wall.\n\n",
                describeAmountOfBottles(current),
                oneOrIt(current),
                describeAmountOfBottles(remaining)
        );
    }

    private String describeAmountOfBottles(int current) {
        switch (current) {
            case 0:
                return "no more bottles";
            case 1:
                return String.format("%s bottle", current);
            default:
                return String.format("%s bottles", current);
        }
    }

    private String oneOrIt(int value) {
        return value == 1 ? "it" : "one" ;
    }

}