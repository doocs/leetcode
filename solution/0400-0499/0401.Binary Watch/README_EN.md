# [401. Binary Watch](https://leetcode.com/problems/binary-watch)

[中文文档](/solution/0400-0499/0401.Binary%20Watch/README.md)

## Description

<p>A binary watch has 4 LEDs on the top which represent the <b>hours</b> (<b>0-11</b>), and the 6 LEDs on the bottom represent the <b>minutes</b> (<b>0-59</b>).</p>

<p>Each LED represents a zero or one, with the least significant bit on the right.</p>

<img src="https://upload.wikimedia.org/wikipedia/commons/8/8b/Binary_clock_samui_moon.jpg" height="300" />

<p>For example, the above binary watch reads "3:25".</p>

<p>Given a non-negative integer <i>n</i> which represents the number of LEDs that are currently on, return all possible times the watch could represent.</p>

<p><b>Example:</b>

<pre>Input: n = 1<br>Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]</pre>

</p>

<p><b>Note:</b><br />

<ul>

<li>The order of output does not matter.</li>

<li>The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".</li>

<li>The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".</li>

</ul>

</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def readBinaryWatch(self, num: int) -> List[str]:
        return ['{:d}:{:02d}'.format(i, j) for i in range(12) for j in range(60) if (bin(i) + bin(j)).count('1') == num]
```

### **Java**

```java
class Solution {
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 12; ++i) {
            for (int j = 0; j < 60; ++j) {
                if (Integer.bitCount(i) + Integer.bitCount(j) == num) {
                    res.add(String.format("%d:%02d", i, j));
                }
            }
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
