# [1386. Cinema Seat Allocation](https://leetcode.com/problems/cinema-seat-allocation)

[中文文档](/solution/1300-1399/1386.Cinema%20Seat%20Allocation/README.md)

## Description

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1386.Cinema%20Seat%20Allocation/images/cinema_seats_1.png" style="width: 400px; height: 149px;" /></p>

<p>A cinema&nbsp;has <code>n</code>&nbsp;rows of seats, numbered from 1 to <code>n</code>&nbsp;and there are ten&nbsp;seats in each row, labelled from 1&nbsp;to 10&nbsp;as shown in the figure above.</p>

<p>Given the array <code>reservedSeats</code> containing the numbers of seats already reserved, for example, <code>reservedSeats[i] = [3,8]</code>&nbsp;means the seat located in row <strong>3</strong> and labelled with <b>8</b>&nbsp;is already reserved.</p>

<p><em>Return the maximum number of four-person groups&nbsp;you can assign on the cinema&nbsp;seats.</em> A four-person group&nbsp;occupies four&nbsp;adjacent seats <strong>in one single row</strong>. Seats across an aisle (such as [3,3]&nbsp;and [3,4]) are not considered to be adjacent, but there is an exceptional case&nbsp;on which an aisle split&nbsp;a four-person group, in that case, the aisle split&nbsp;a four-person group in the middle,&nbsp;which means to have two people on each side.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1386.Cinema%20Seat%20Allocation/images/cinema_seats_3.png" style="width: 400px; height: 96px;" /></p>

<pre>
<strong>Input:</strong> n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The figure above shows the optimal allocation for four groups, where seats mark with blue are already reserved and contiguous seats mark with orange are for one group.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2, reservedSeats = [[2,1],[1,8],[2,6]]
<strong>Output:</strong> 2
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 4, reservedSeats = [[4,3],[1,4],[4,6],[1,7]]
<strong>Output:</strong> 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10^9</code></li>
	<li><code>1 &lt;=&nbsp;reservedSeats.length &lt;= min(10*n, 10^4)</code></li>
	<li><code>reservedSeats[i].length == 2</code></li>
	<li><code>1&nbsp;&lt;=&nbsp;reservedSeats[i][0] &lt;= n</code></li>
	<li><code>1 &lt;=&nbsp;reservedSeats[i][1] &lt;= 10</code></li>
	<li>All <code>reservedSeats[i]</code> are distinct.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxNumberOfFamilies(self, n: int, reservedSeats: List[List[int]]) -> int:
        m = defaultdict(int)
        for i, j in reservedSeats:
            m[i] = m[i] | (1 << (10 - j))
        masks = (0b0111100000, 0b0000011110, 0b0001111000)
        ans = (n - len(m)) << 1
        for v in m.values():
            for mask in masks:
                if (v & mask) == 0:
                    v |= mask
                    ans += 1
        return ans
```

### **Java**

```java
class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int[] e : reservedSeats) {
            int i = e[0], j = 10 - e[1];
            int v = m.getOrDefault(i, 0);
            v |= 1 << j;
            m.put(i, v);
        }
        int[] masks = {0b0111100000, 0b0000011110, 0b0001111000};
        int ans = (n - m.size()) << 1;
        for (int v : m.values()) {
            for (int mask : masks) {
                if ((v & mask) == 0) {
                    v |= mask;
                    ++ans;
                }
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
    int maxNumberOfFamilies(int n, vector<vector<int>>& reservedSeats) {
        unordered_map<int, int> m;
        for (auto& e : reservedSeats) {
            int i = e[0], j = 10 - e[1];
            m[i] |= (1 << j);
        }
        vector<int> masks = {0b0111100000, 0b0000011110, 0b0001111000};
        int ans = (n - m.size()) << 1;
        for (auto& [_, v] : m) {
            for (int& mask : masks) {
                if ((v & mask) == 0) {
                    v |= mask;
                    ++ans;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maxNumberOfFamilies(n int, reservedSeats [][]int) int {
	m := map[int]int{}
	for _, e := range reservedSeats {
		i, j := e[0], 10-e[1]
		m[i] |= 1 << j
	}
	masks := []int{0b0111100000, 0b0000011110, 0b0001111000}
	ans := (n - len(m)) << 1
	for _, v := range m {
		for _, mask := range masks {
			if (v & mask) == 0 {
				v |= mask
				ans++
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
