# [744. Find Smallest Letter Greater Than Target](https://leetcode.com/problems/find-smallest-letter-greater-than-target)

[中文文档](/solution/0700-0799/0744.Find%20Smallest%20Letter%20Greater%20Than%20Target/README.md)

## Description

<p>

Given a list of sorted characters <code>letters</code> containing only lowercase letters, and given a target letter <code>target</code>, find the smallest element in the list that is larger than the given target.

</p><p>

Letters also wrap around. For example, if the target is <code>target = 'z'</code> and <code>letters = ['a', 'b']</code>, the answer is <code>'a'</code>.

</p>

<p><b>Examples:</b><br />

<pre>

<b>Input:</b>

letters = ["c", "f", "j"]

target = "a"

<b>Output:</b> "c"



<b>Input:</b>

letters = ["c", "f", "j"]

target = "c"

<b>Output:</b> "f"



<b>Input:</b>

letters = ["c", "f", "j"]

target = "d"

<b>Output:</b> "f"



<b>Input:</b>

letters = ["c", "f", "j"]

target = "g"

<b>Output:</b> "j"



<b>Input:</b>

letters = ["c", "f", "j"]

target = "j"

<b>Output:</b> "c"



<b>Input:</b>

letters = ["c", "f", "j"]

target = "k"

<b>Output:</b> "c"

</pre>

</p>

<p><b>Note:</b><br>

<ol>

<li><code>letters</code> has a length in range <code>[2, 10000]</code>.</li>

<li><code>letters</code> consists of lowercase letters, and contains at least 2 unique letters.</li>

<li><code>target</code> is a lowercase letter.</li>

</ol>

</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def nextGreatestLetter(self, letters: List[str], target: str) -> str:
        left, right = 0, len(letters)
        while left < right:
            mid = (left + right) >> 1
            if ord(letters[mid]) > ord(target):
                right = mid
            else:
                left = mid + 1
        return letters[left % len(letters)]
```

### **Java**

```java
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0, right = letters.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (letters[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return letters[left % letters.length];
    }
}
```

### **TypeScript**

```ts
function nextGreatestLetter(letters: string[], target: string): string {
    let left = 0,
        right = letters.length;
    let x = target.charCodeAt(0);
    while (left < right) {
        let mid = (left + right) >> 1;
        if (x < letters[mid].charCodeAt(0)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return letters[left % letters.length];
}
```

### **C++**

```cpp
class Solution {
public:
    char nextGreatestLetter(vector<char>& letters, char target) {
        int left = 0, right = letters.size();
        while (left < right) {
            int mid = left + right >> 1;
            if (letters[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return letters[left % letters.size()];
    }
};
```

### **Go**

```go
func nextGreatestLetter(letters []byte, target byte) byte {
	left, right := 0, len(letters)
	for left < right {
		mid := (left + right) >> 1
		if letters[mid] > target {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return letters[left%len(letters)]
}
```

### **...**

```

```

<!-- tabs:end -->
