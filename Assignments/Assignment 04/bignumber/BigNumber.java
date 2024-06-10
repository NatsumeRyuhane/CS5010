package bignumber;

public interface BigNumber extends Comparable<BigNumber> {
    /**
     * @return the number of digits in this number
     */
    int length();

    /**
     * takes the number of shifts as an argument and shifts this number to the left by that number
     * left-shifting 0 will yield 0
     * a negative number of left-shifts will correspond to those many right shifts
     *
     * @return the BigNumber after shifting
     */
    BigNumber shiftLeft(int n);

    /**
     * takes the number of shifts as an argument and shifts this number to the right by that number
     * the number 0 can be right-shifted any positive number of times, yielding the same number 0
     * A negative number of right-shifts will correspond to those many left shifts.
     *
     * @return the BigNumber after shifting
     */
    BigNumber shiftRight(int n);

    /**
     * takes a single digit as an argument and adds it to this number arithmetically
     *
     * @param digit the digit to be added
     * @return the BigNumber after adding the digit
     * @throws IllegalArgumentException if the digit is not between 0 and 9
     */
    BigNumber addDigit(int digit);

    /**
     * takes a position as an argument and returns the digit at that position
     * Positions start at 0 (rightmost digit)
     *
     * @param index the index of the digit
     * @return the digit at the specified index
     * @throws IllegalArgumentException if an invalid position is passed.
     */
    int getDigitAt(int index);

    /**
     * returns an identical and independent copy of this number
     *
     * @return a copy of the BigNumber
     */
    BigNumber copy();

    /**
     * takes another BigNumber and returns the sum of these two numbers
     * this operation does not change either number
     *
     * @param other the BigNumber to be added
     * @return the BigNumber after adding
     */
    BigNumber add(BigNumber other);
    /**
     * Compares this BigNumber with the specified BigNumber.
     *
     * @param other the BigNumber to be compared
     * @return a negative integer, zero, or a positive integer as this BigNumber is less than, equal to, or greater than the specified BigNumber
     */
    int compareTo(BigNumber other);
}
