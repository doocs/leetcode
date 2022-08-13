# [401. Binary Watch](https://leetcode.com/problems/binary-watch)

[中文文档](/solution/0400-0499/0401.Binary%20Watch/README.md)

## Description

<p>A binary watch has 4 LEDs on the top to represent the hours (0-11), and 6 LEDs on the bottom to represent&nbsp;the minutes (0-59). Each LED represents a zero or one, with the least significant bit on the right.</p>

<ul>
	<li>For example, the below binary watch reads <code>&quot;4:51&quot;</code>.</li>
</ul>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0401.Binary%20Watch/images/binarywatch.jpg" style="width: 500px; height: 500px;" /></p>

<p>Given an integer <code>turnedOn</code> which represents the number of LEDs that are currently on (ignoring the PM), return <em>all possible times the watch could represent</em>. You may return the answer in <strong>any order</strong>.</p>

<p>The hour must not contain a leading zero.</p>

<ul>
	<li>For example, <code>&quot;01:00&quot;</code> is not valid. It should be <code>&quot;1:00&quot;</code>.</li>
</ul>

<p>The minute must be consist of two digits and may contain a leading zero.</p>

<ul>
	<li>For example, <code>&quot;10:2&quot;</code> is not valid. It should be <code>&quot;10:02&quot;</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> turnedOn = 1
<strong>Output:</strong> ["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> turnedOn = 9
<strong>Output:</strong> []
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= turnedOn &lt;= 10</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def readBinaryWatch(self, turnedOn: int) -> List[str]:
        return [
            '{:d}:{:02d}'.format(i, j)
            for i in range(12)
            for j in range(60)
            if (bin(i) + bin(j)).count('1') == turnedOn
        ]
```

```python
class Solution:
    def readBinaryWatch(self, turnedOn: int) -> List[str]:
        ans = []
        for i in range(1 << 10):
            h, m = i >> 6, i & 0b111111
            if h < 12 and m < 60 and i.bit_count() == turnedOn:
                ans.append('{:d}:{:02d}'.format(h, m))
        return ans
```

### **Java**

```java
class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 12; ++i) {
            for (int j = 0; j < 60; ++j) {
                if (Integer.bitCount(i) + Integer.bitCount(j) == turnedOn) {
                    ans.add(String.format("%d:%02d", i, j));
                }
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 1 << 10; ++i) {
            int h = i >> 6, m = i & 0b111111;
            if (h < 12 && m < 60 && Integer.bitCount(i) == turnedOn) {
                ans.add(String.format("%d:%02d", h, m));
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> readBinaryWatch(int turnedOn) {
        vector<string> ans;
        for (int i = 0; i < 12; ++i) {
            for (int j = 0; j < 60; ++j) {
                if (__builtin_popcount(i) + __builtin_popcount(j) == turnedOn) {
                    ans.push_back(to_string(i) + ":" + (j < 10 ? "0" : "") + to_string(j));
                }
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    vector<string> readBinaryWatch(int turnedOn) {
        vector<string> ans;
        for (int i = 0; i < 1 << 10; ++i) {
            int h = i >> 6, m = i & 0b111111;
            if (h < 12 && m < 60 && __builtin_popcount(i) == turnedOn) {
                ans.push_back(to_string(h) + ":" + (m < 10 ? "0" : "") + to_string(m));
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func readBinaryWatch(turnedOn int) []string {
	var ans []string
	for i := 0; i < 12; i++ {
		for j := 0; j < 60; j++ {
			if bits.OnesCount(uint(i))+bits.OnesCount(uint(j)) == turnedOn {
				ans = append(ans, fmt.Sprintf("%d:%02d", i, j))
			}
		}
	}
	return ans
}
```

```go
func readBinaryWatch(turnedOn int) []string {
	var ans []string
	for i := 0; i < 1<<10; i++ {
		h, m := i>>6, i&0b111111
		if h < 12 && m < 60 && bits.OnesCount(uint(i)) == turnedOn {
			ans = append(ans, fmt.Sprintf("%d:%02d", h, m))
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function readBinaryWatch(turnedOn: number): string[] {
    if (turnedOn === 0) {
        return ['0:00'];
    }
    const n = 10;
    const res = [];
    const bitArr = new Array(10).fill(false);
    const createTime = () => {
        return [
            bitArr.slice(0, 4).reduce((p, v) => (p << 1) | Number(v), 0),
            bitArr.slice(4).reduce((p, v) => (p << 1) | Number(v), 0),
        ];
    };
    const helper = (i: number, count: number) => {
        if (i + count > n || count === 0) {
            return;
        }
        bitArr[i] = true;
        if (count === 1) {
            const [h, m] = createTime();
            if (h < 12 && m < 60) {
                res.push(`${h}:${m < 10 ? '0' + m : m}`);
            }
        }
        helper(i + 1, count - 1);
        bitArr[i] = false;
        helper(i + 1, count);
    };
    helper(0, turnedOn);
    return res;
}
```

### **Rust**

```rust
impl Solution {
    fn create_time(bit_arr: &[bool; 10]) -> (i32, i32) {
        let mut h = 0;
        let mut m = 0;
        for i in 0..4 {
            h <<= 1;
            h |= if bit_arr[i] { 1 } else { 0 };
        }
        for i in 4..10 {
            m <<= 1;
            m |= if bit_arr[i] { 1 } else { 0 };
        }

        (h, m)
    }

    fn helper(res: &mut Vec<String>, bit_arr: &mut [bool; 10], i: usize, count: usize) {
        if i + count > 10 || count == 0 {
            return;
        }
        bit_arr[i] = true;
        if count == 1 {
            let (h, m) = Self::create_time(bit_arr);
            if h < 12 && m < 60 {
                if m < 10 {
                    res.push(format!("{}:0{}", h, m));
                } else {
                    res.push(format!("{}:{}", h, m));
                }
            }
        }
        Self::helper(res, bit_arr, i + 1, count - 1);
        bit_arr[i] = false;
        Self::helper(res, bit_arr, i + 1, count);
    }

    pub fn read_binary_watch(turned_on: i32) -> Vec<String> {
        if turned_on == 0 {
            return vec![String::from("0:00")];
        }
        let mut res = vec![];
        let mut bit_arr = [false; 10];
        Self::helper(&mut res, &mut bit_arr, 0, turned_on as usize);
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
