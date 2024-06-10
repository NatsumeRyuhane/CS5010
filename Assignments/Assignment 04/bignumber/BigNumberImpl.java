package bignumber;

public class BigNumberImpl implements BigNumber{

    private CustomLinkedList numbers = new CustomLinkedList();

    public BigNumberImpl() {
        this.numbers.append(0);
    }

    public BigNumberImpl(String number) {
        try {
            for (int i = 0; i < number.length(); i++) {
                int num = Character.getNumericValue(number.charAt(i));
                if (num >= 0 && num <= 9) {
                    this.numbers.append(num);
                }
                else {
                    throw new IllegalArgumentException("Invalid input");
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid input");
        }
    }

    @Override
    public int length() {
        return this.numbers.length();
    }

    @Override
    public BigNumber shiftLeft(int n) {
        if (n < 0) {
            return this.shiftRight(-n);
        }

        if (this.length() == 1) {
            if (this.numbers.getValueAt(0) == 0) {
                return this;
            }
        }

        // use a temporary BigNumber to store the result
        // to hold the atomicity of the operation
        BigNumberImpl result = (BigNumberImpl) this.copy();

        for (int i = 0; i < n; i++) {
            result.numbers.append(0);
        }

        this.numbers = result.numbers;
        return this;
    }

    @Override
    public BigNumber shiftRight(int n) {
        if (n < 0) {
            return this.shiftLeft(-n);
        }

        if (this.length() == 1) {
            this.numbers.get(0).setData(0);
            return this;
        }

        // use a temporary BigNumber to store the result
        // to hold the atomicity of the operation
        BigNumberImpl result = (BigNumberImpl) this.copy();

        for (int i = 0; i < n; i++) {

            if (result.numbers.length() == 1) {
                    result.numbers.get(0).setData(0);
                    break;
                }
            result.numbers.pop(-1);
        }

        this.numbers = result.numbers;
        return this;
    }

    @Override
    public BigNumber addDigit(int digit) {
        if (digit < 0 || digit > 9) {
            throw new IllegalArgumentException("Invalid digit");
        }

        for (int i = -1; -i <= this.length(); i--) {
            int sum = this.numbers.getValueAt(i) + digit;
            digit = 0;
            if (sum > 9) {
                digit = 1;
                sum = sum % 10;
            }

            this.numbers.get(i).setData(sum);
            if (digit == 0) {
                break;
            }
        }

        if (digit > 0) {
            this.numbers.insert(digit, 0);
        }

        return this;
    }

    @Override
    public int getDigitAt(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Invalid index");
        }

        try {
            // NOTE: the least significant digit is at index 0
            //       however, the index designed for the linked list is at the head of the list
            //       thus the digit in the list is at position (-1 - index)
            return this.numbers.getValueAt(-1 - index);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Invalid index");
        }
    }

    @Override
    public BigNumber copy() {
        return new BigNumberImpl(this.toString());
    }

    @Override
    public BigNumber add(BigNumber other) {
        BigNumberImpl result = new BigNumberImpl();

        // calculate the maximum length of the two numbers
        int thisNumberLength = this.length();
        int otherNumberLength = other.length();
        int maxLength = Math.max(thisNumberLength, otherNumberLength);
        int carry = 0;

        // iterate through the maximum length of the two numbers
        for(int i = 0; i < maxLength; i++) {
            int thisDigit = 0;
            int otherDigit = 0;

            if (i < thisNumberLength) {
                thisDigit = this.getDigitAt(i);
            }

            if (i < otherNumberLength) {
                otherDigit = other.getDigitAt(i);
            }

            // add the digits and the carry
            int sum = thisDigit + otherDigit + carry;
            if (sum > 9) {
                carry = 1;
                sum = sum % 10;
            }
            else {
                carry = 0;
            }

            // NOTE: this will append the sum as the most significant digit
            //       however, the number is constructed with a start value of 0, not null
            result.addMostSignificantDigit(sum);
        }

        // if there is a leftover carry, add it as the most significant digit
        if (carry == 1) { result.addMostSignificantDigit(1); }

        // remove the initial leftover 0 on the least significant digit
        result.shiftRight(1);

        return result;
    }

    @Override
    public int compareTo(BigNumber other) {
        int thisNumberLength = this.length();
        int otherNumberLength = other.length();

        if (thisNumberLength > otherNumberLength) {
            return 1;
        }
        else if (thisNumberLength < otherNumberLength) {
            return -1;
        }
        else {
            for (int i = thisNumberLength - 1; i >= 0; i--) {
                int thisDigit = this.getDigitAt(i);
                int otherDigit = other.getDigitAt(i);
                if (thisDigit > otherDigit) {
                    return 1;
                }
                else if (thisDigit < otherDigit) {
                    return -1;
                }
            }
            return 0;
        }
    }

    @Override
    public String toString() {
        return this.numbers.toString();
    }

    /**
     * @return the list representation of the number
     */
    public String toListRepr() {
        return this.numbers.toListRepr();
    }

    /**
     * adds a digit to the most significant digit of the number
     * @param digit the digit to be added
     * @return the BigNumber after adding the digit
     */
    public BigNumber addMostSignificantDigit(int digit) {
        this.numbers.insert(digit, 0);
        return this;
    }
}
