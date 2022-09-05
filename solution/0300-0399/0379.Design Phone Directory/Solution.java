class PhoneDirectory {

    private boolean[] provided;

    /**
       Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
     */
    public PhoneDirectory(int maxNumbers) {
        provided = new boolean[maxNumbers];
    }

    /**
       Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
     */
    public int get() {
        for (int i = 0; i < provided.length; ++i) {
            if (!provided[i]) {
                provided[i] = true;
                return i;
            }
        }
        return -1;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !provided[number];
    }

    /** Recycle or release a number. */
    public void release(int number) {
        provided[number] = false;
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */