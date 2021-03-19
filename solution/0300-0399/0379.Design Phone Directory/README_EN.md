# [379. Design Phone Directory](https://leetcode.com/problems/design-phone-directory)

[中文文档](/solution/0300-0399/0379.Design%20Phone%20Directory/README.md)

## Description

<p>Design a Phone Directory which supports the following operations:</p>

<p> </p>

<ol>
	<li><code>get</code>: Provide a number which is not assigned to anyone.</li>
	<li><code>check</code>: Check if a number is available or not.</li>
	<li><code>release</code>: Recycle or release a number.</li>
</ol>

<p> </p>

<p><b>Example:</b></p>

<pre>
// Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
PhoneDirectory directory = new PhoneDirectory(3);

// It can return any available phone number. Here we assume it returns 0.
directory.get();

// Assume it returns 1.
directory.get();

// The number 2 is available, so return true.
directory.check(2);

// It returns 2, the only number that is left.
directory.get();

// The number 2 is no longer available, so return false.
directory.check(2);

// Release number 2 back to the pool.
directory.release(2);

// Number 2 is available again, return true.
directory.check(2);
</pre>

<p> </p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 <= maxNumbers <= 10^4</code></li>
	<li><code>0 <= number < maxNumbers</code></li>
	<li>The total number of call of the methods is between <code>[0 - 20000]</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        """
        self.provided = [False] * maxNumbers

    def get(self) -> int:
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        """
        for i in range(len(self.provided)):
            if not self.provided[i]:
                self.provided[i] = True
                return i
        return -1

    def check(self, number: int) -> bool:
        """
        Check if a number is available or not.
        """
        return not self.provided[number]

    def release(self, number: int) -> None:
        """
        Recycle or release a number.
        """
        self.provided[number] = False


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)
```

### **Java**

```java
class PhoneDirectory {

    private boolean[] provided;

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        provided = new boolean[maxNumbers];
    }

    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
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
```

### **...**

```

```

<!-- tabs:end -->
