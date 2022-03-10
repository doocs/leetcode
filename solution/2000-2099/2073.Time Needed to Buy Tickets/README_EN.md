# [2073. Time Needed to Buy Tickets](https://leetcode.com/problems/time-needed-to-buy-tickets)

[中文文档](/solution/2000-2099/2073.Time%20Needed%20to%20Buy%20Tickets/README.md)

## Description

<p>There are <code>n</code> people in a line queuing to buy tickets, where the <code>0<sup>th</sup></code> person is at the <strong>front</strong> of the line and the <code>(n - 1)<sup>th</sup></code> person is at the <strong>back</strong> of the line.</p>

<p>You are given a <strong>0-indexed</strong> integer array <code>tickets</code> of length <code>n</code> where the number of tickets that the <code>i<sup>th</sup></code> person would like to buy is <code>tickets[i]</code>.</p>

<p>Each person takes <strong>exactly 1 second</strong> to buy a ticket. A person can only buy <strong>1 ticket at a time</strong> and has to go back to <strong>the end</strong> of the line (which happens <strong>instantaneously</strong>) in order to buy more tickets. If a person does not have any tickets left to buy, the person will <strong>leave </strong>the line.</p>

<p>Return <em>the <strong>time taken</strong> for the person at position </em><code>k</code><em>&nbsp;</em><strong><em>(0-indexed)</em>&nbsp;</strong><em>to finish buying tickets</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> tickets = [2,3,2], k = 2
<strong>Output:</strong> 6
<strong>Explanation:</strong> 
- In the first pass, everyone in the line buys a ticket and the line becomes [1, 2, 1].
- In the second pass, everyone in the line buys a ticket and the line becomes [0, 1, 0].
The person at&nbsp;position 2 has successfully bought 2 tickets and it took 3 + 3 = 6 seconds.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> tickets = [5,1,1,1], k = 0
<strong>Output:</strong> 8
<strong>Explanation:</strong>
- In the first pass, everyone in the line buys a ticket and the line becomes [4, 0, 0, 0].
- In the next 4 passes, only the person in position 0 is buying tickets.
The person at&nbsp;position 0 has successfully bought 5 tickets and it took 4 + 1 + 1 + 1 + 1 = 8 seconds.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == tickets.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= tickets[i] &lt;= 100</code></li>
	<li><code>0 &lt;= k &lt; n</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def timeRequiredToBuy(self, tickets: List[int], k: int) -> int:
        ans = 0
        for i, t in enumerate(tickets):
            if i <= k:
                ans += min(tickets[k], t)
            else:
                ans += min(tickets[k] - 1, t)
        return ans
```

### **Java**

```java
class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {
        int ans = 0;
        for (int i = 0; i < tickets.length; i++) {
            if (i <= k) {
                ans += Math.min(tickets[k], tickets[i]);
            } else {
                ans += Math.min(tickets[k] - 1, tickets[i]);
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function timeRequiredToBuy(tickets: number[], k: number): number {
    const n = tickets.length;
    let target = tickets[k] - 1;
    let ans = 0;
    // round1
    for (let i = 0; i < n; i++) {
        let num = tickets[i];
        if (num <= target) {
            ans += num;
            tickets[i] = 0;
        } else {
            ans += target;
            tickets[i] -= target;
        }
    }

    // round2
    for (let i = 0; i <= k; i++) {
        let num = tickets[i];
        ans += num > 0 ? 1 : 0;
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int timeRequiredToBuy(vector<int>& tickets, int k) {
        int ans = 0;
        for (int i = 0; i < tickets.size(); ++i) {
            if (i <= k) {
                ans += min(tickets[k], tickets[i]);
            } else {
                ans += min(tickets[k] - 1, tickets[i]);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func timeRequiredToBuy(tickets []int, k int) int {
	ans := 0
	for i, t := range tickets {
		if i <= k {
			ans += min(tickets[k], t)
		} else {
			ans += min(tickets[k]-1, t)
		}
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
