# [401. 二进制手表](https://leetcode.cn/problems/binary-watch)

[English Version](/solution/0400-0499/0401.Binary%20Watch/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>二进制手表顶部有 4 个 LED 代表<strong> 小时（0-11）</strong>，底部的 6 个 LED 代表<strong> 分钟（0-59）</strong>。每个 LED 代表一个 0 或 1，最低位在右侧。</p>

<ul>
	<li>例如，下面的二进制手表读取 <code>"3:25"</code> 。</li>
</ul>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0401.Binary%20Watch/images/binary_clock_samui_moon.jpg" style="height: 300px; width" /></p>

<p><small><em>（图源：<a href="https://commons.m.wikimedia.org/wiki/File:Binary_clock_samui_moon.jpg">WikiMedia - Binary clock samui moon.jpg</a> ，许可协议：<a href="https://creativecommons.org/licenses/by-sa/3.0/deed.en">Attribution-ShareAlike 3.0 Unported (CC BY-SA 3.0)</a> ）</em></small></p>

<p>给你一个整数 <code>turnedOn</code> ，表示当前亮着的 LED 的数量，返回二进制手表可以表示的所有可能时间。你可以 <strong>按任意顺序</strong> 返回答案。</p>

<p>小时不会以零开头：</p>

<ul>
	<li>例如，<code>"01:00"</code> 是无效的时间，正确的写法应该是 <code>"1:00"</code> 。</li>
</ul>

<p>分钟必须由两位数组成，可能会以零开头：</p>

<ul>
	<li>例如，<code>"10:2"</code> 是无效的时间，正确的写法应该是 <code>"10:02"</code> 。</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>turnedOn = 1
<strong>输出：</strong>["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>turnedOn = 9
<strong>输出：</strong>[]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= turnedOn <= 10</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：枚举组合**

题目可转换为求 i(`i∈[0,12)`) 和 j(`j∈[0,60)`) 所有可能的组合。

合法组合需要满足的条件是：i 的二进制形式中 1 的个数加上 j 的二进制形式中 1 的个数，结果等于 turnedOn。

**方法二：二进制枚举**

利用 10 个二进制位表示手表，其中前 4 位代表小时，后 6 位代表分钟。枚举 `[0, 1 << 10)` 的所有数，找出合法的数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
