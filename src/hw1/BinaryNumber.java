package hw1;
/**
 * I pledge my honor that I have abided by the Stevens Honor System. -cli59
 * @author christinali
 * This class creates binary numbers
 */
public class BinaryNumber {
	//Data fields
	private int[] data;
	private boolean overflow;
	
	//Constructor
	/**
	 * Given the length, this creates a binary number consisting only of zeros
	 * @param length the binary number of length
	 */
	public BinaryNumber(int length){
		data = new int[length];
	}
	/**
	 * Given the str, this creates the binary number
	 * @param str the binary number 
	 */
	public BinaryNumber(String str){
		data = new int[str.length()];
		for(int i=0; i < str.length(); i++) {
			data[i] = java.lang.Character.getNumericValue(str.charAt(i));
		}
	}
	
	//Methods
	/**
	 * Returns the length of the binary number
	 * @return length of binary number
	 */
	public int getLength() {
		return data.length;
	}
	/**
	 * Obtains a digit of the binary number given the index.
	 * If the index is out of bounds, a message will appear.
	 * @param index The index of the digit
	 * @return The digit of the binary number at the index
	 */
	public int getDigit(int index) {
		if(index < 0 || index > this.getLength()) {
			System.out.println("Index is out of bounds"); 
			return -1;
		}else {
			return data[index];
		}
	}
	/**
	 * Transforms a binary number to its decimal notation
	 * @return a decimal number equivalent to its binary number
	 */
	public int toDecimal() {
		int decimal = 0;
		for (int i=0; i < data.length; i++) {
			decimal += data[i] * Math.pow(2,i);
		}
		return decimal;
	}
	/**
	 * This parameter shifts all the digits in a binary number any number of places to the right
	 * @param amount The amount of spaces to the right that the binary number will be shifted
	 */
	public void shiftR(int amount) {
		if (amount < 0) {
			System.out.println("Amount needs to be greater than or equal to 0.");
		}
		int past[] = data;
		data = new int[amount + past.length];
		for (int i = 0; i < amount; i++) {
			data[i] = 0;
		}
		for (int i = amount; i < data.length; i++) {
			data[i] = past[i - amount];
		}
	}
	/**
	 * Message will appear if the lengths of BinaryNumber and aBinaryNumber do not coincide.
	 * This method modifies binary number with the result of the addition with another binary number
	 * given the parameter.
	 * @param aBinaryNumber The binary number being added to the other binary number.
	 */
	public void add(BinaryNumber aBinaryNumber) {
		if (this.getLength() != aBinaryNumber.getLength()) {
            System.out.println("The length of the binary numbers do not coincide.");
        }
        int carry = 0;
        for(int i=0; i<data.length; i++){
            if(carry + data[i] + aBinaryNumber.getDigit(i) < 2){
                data[i] = carry + data[i] + aBinaryNumber.getDigit(i);
                carry = 0;
            }else if(carry + data[i] + aBinaryNumber.getDigit(i) == 2){
                data[i] = 0;
                carry = 1;
            }else if(carry + data[i] + aBinaryNumber.getDigit(i) == 3){
                data[i] = 1;
                carry = 1;
            }
        }
        if(carry > 0){
            overflow = true;
        }
    }
	/**
	 * Sets the overflow parameter to false to clear the overflow flag
	 */
	public void clearOverflow() {
		overflow = false;
	}	
	/**
	 * Transform a binary number into a string
	 * If the number results in an overflow, the string "overflow" is returned
	 */
	public String toString() {
		if (overflow) {
			return "Overflow";
		}else {
			String num = "";
			for(int i=0; i < data.length; i++) {
				num += data[i];
			}
			return num;
		}
	}	
	public static void main(String[] args) {

	}
}

