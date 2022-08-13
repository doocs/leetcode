# [379. Design Phone Directory](https://leetcode.com/problems/design-phone-directory)

[中文文档](/solution/0300-0399/0379.Design%20Phone%20Directory/README.md)

## Description

<p>Design a phone directory that initially has <code>maxNumbers</code> empty slots that can store numbers. The directory should store numbers, check if a certain slot is empty or not, and empty a given slot.</p>

<p>Implement the <code>PhoneDirectory</code> class:</p>

<ul>
	<li><code>PhoneDirectory(int maxNumbers)</code> Initializes the phone directory with the number of available slots <code>maxNumbers</code>.</li>
	<li><code>int get()</code> Provides a number that is not assigned to anyone. Returns <code>-1</code> if no number is available.</li>
	<li><code>bool check(int number)</code> Returns <code>true</code> if the slot <code>number</code> is available and <code>false</code> otherwise.</li>
	<li><code>void release(int number)</code> Recycles or releases the slot <code>number</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;PhoneDirectory&quot;, &quot;get&quot;, &quot;get&quot;, &quot;check&quot;, &quot;get&quot;, &quot;check&quot;, &quot;release&quot;, &quot;check&quot;]
[[3], [], [], [2], [], [2], [2], [2]]
<strong>Output</strong>
[null, 0, 1, true, 2, false, null, true]

<strong>Explanation</strong>
PhoneDirectory phoneDirectory = new PhoneDirectory(3);
phoneDirectory.get();      // It can return any available phone number. Here we assume it returns 0.
phoneDirectory.get();      // Assume it returns 1.
phoneDirectory.check(2);   // The number 2 is available, so return true.
phoneDirectory.get();      // It returns 2, the only number that is left.
phoneDirectory.check(2);   // The number 2 is no longer available, so return false.
phoneDirectory.release(2); // Release number 2 back to the pool.
phoneDirectory.check(2);   // Number 2 is available again, return true.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= maxNumbers &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= number &lt; maxNumbers</code></li>
	<li>At most <code>2 * 10<sup>4</sup></code> calls will be made to <code>get</code>, <code>check</code>, and <code>release</code>.</li>
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
