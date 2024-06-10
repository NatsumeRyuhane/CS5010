package lookandsay;

import java.math.BigInteger;
import java.util.NoSuchElementException;

public class LookAndSayIterator implements RIterator<BigInteger> {
    // the next number to be offered
    private BigInteger number;
    // the limit of the sequence
    // this.number should not exceed this.limit
    private final BigInteger limit;

    /**
     * Constructs a LookAndSayIterator with a given seed and limit
     *
     * @param seed the seed number
     * @param limit the max allowed number
     */
    public LookAndSayIterator(BigInteger seed, BigInteger limit) {
        if (seed.compareTo(BigInteger.ZERO) <= 0 || limit.compareTo(BigInteger.ZERO) <= 0) {
            throw new IllegalArgumentException("Seed and limit must be positive");

        }

        if (seed.compareTo(limit) > 0) {
            throw new IllegalArgumentException (
                    "Seed" + " [" + seed.toString() + "] " +
                            "should be less than or equal to limit" + " [" + limit.toString() + "]"
            );
        }

        for(char c: seed.toString().toCharArray()) {
            if(c == '0') {
                throw new IllegalArgumentException("Seed cannot contain 0");
            }
        }

        this.number = seed;
        this.limit = limit;
    }

    /**
     * Constructs a LookAndSayIterator with a given seed and a default limit of 10^100
     *
     * @param seed the seed number
     */
    public LookAndSayIterator(BigInteger seed) {
        this(seed, BigInteger.TEN.pow(100));
    }

    /**
     * Constructs a LookAndSayIterator with a default seed of 1 and a default limit of 10^100
     */
    public LookAndSayIterator() {
        this(BigInteger.ONE);
    }

    /**
     * @return {@code true} it is possible to construct the next element within range
     */
    @Override
    public boolean hasNext() {
        try {
            if (this.number.compareTo(this.limit) > 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Constructs the next number based on the current state
     * this method does not advance the state, it only constructs the next number
     *
     * @return the next constructed number
     */
    private BigInteger constructNext() {
        BigInteger count = BigInteger.ZERO;
        String currentString = this.number.toString();
        char currentChar = currentString.charAt(0);

        StringBuilder nextString = new StringBuilder();

        // Count the number of times the current character appears
        for (char c : currentString.toCharArray()) {
            if (c == currentChar) {
                count = count.add(BigInteger.ONE);
            } else {
                // if the character changes, append the count and the character to the nextString
                nextString.append(count).append(currentChar);
                currentChar = c;
                count = BigInteger.ONE;
            }
        }

        // append the last count and character
        nextString.append(count).append(currentChar);

        return new BigInteger(nextString.toString());
    }

    /**
     * Returns the current number and advances the next number
     *
     * @return the next constructed number
     */
    @Override
    public BigInteger next() {
        BigInteger currentNumber = new BigInteger(this.number.toString());
        this.number = this.constructNext();
        return currentNumber;
    }

    /**
     * @return {@code true} if it is possible to construct the previous number within range
     */
    @Override
    public boolean hasPrevious() {
        try {
            // try constructing the previous number and see if it is within range
            BigInteger test = this.constructPrevious();

            // if no exception is thrown,
            // then it is possible to construct the previous number
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    /**
     * Constructs the previous number based on the current state
     * this method does not retreat the state, it only constructs the previous number
     *
     * @return the previous number constructed from the current number
     * @throws NoSuchElementException if the previous number is impossible to construct
     */
    public BigInteger constructPrevious() {
        String currentString = this.number.toString();
        StringBuilder prevString = new StringBuilder();

        // if the current number has an odd number of digits,
        // it is not possible to construct the previous number
        if (currentString.length() % 2 != 0) {
            throw new NoSuchElementException("Previous number is impossible to construct");
        }

        for (int i = 0; i < currentString.length(); i += 2) {
            // append the character at index i+1 for i times
            prevString.append(
                    String.valueOf(currentString.charAt(i+1))
                            .repeat(Integer.parseInt(String.valueOf(currentString.charAt(i))))
            );
        }

        return new BigInteger(prevString.toString());
    }

    /**
     * Returns the current number and retreats to the previous number
     *
     * @return the previous number
     */
    @Override
    public BigInteger prev() {
        BigInteger currentNumber = new BigInteger(this.number.toString());

        try {
            this.number = this.constructPrevious();

        } catch (Exception e) {
            // do nothing?
        }

        return currentNumber;
    }
}
