# [2383. Minimum Hours of Training to Win a Competition](https://leetcode.com/problems/minimum-hours-of-training-to-win-a-competition)

[中文文档](/solution/2300-2399/2383.Minimum%20Hours%20of%20Training%20to%20Win%20a%20Competition/README.md)

## Description

<p>You are entering a competition, and are given two <strong>positive</strong> integers <code>initialEnergy</code> and <code>initialExperience</code> denoting your initial energy and initial experience respectively.</p>

<p>You are also given two <strong>0-indexed</strong> integer arrays <code>energy</code> and <code>experience</code>, both of length <code>n</code>.</p>

<p>You will face <code>n</code> opponents <strong>in order</strong>. The energy and experience of the <code>i<sup>th</sup></code> opponent is denoted by <code>energy[i]</code> and <code>experience[i]</code> respectively. When you face an opponent, you need to have both <strong>strictly</strong> greater experience and energy to defeat them and move to the next opponent if available.</p>

<p>Defeating the <code>i<sup>th</sup></code> opponent <strong>increases</strong> your experience by <code>experience[i]</code>, but <strong>decreases</strong> your energy by <code>energy[i]</code>.</p>

<p>Before starting the competition, you can train for some number of hours. After each hour of training, you can <strong>either</strong> choose to increase your initial experience by one, or increase your initial energy by one.</p>

<p>Return <em>the <strong>minimum</strong> number of training hours required to defeat all </em><code>n</code><em> opponents</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> initialEnergy = 5, initialExperience = 3, energy = [1,4,3,2], experience = [2,6,3,1]
<strong>Output:</strong> 8
<strong>Explanation:</strong> You can increase your energy to 11 after 6 hours of training, and your experience to 5 after 2 hours of training.
You face the opponents in the following order:
- You have more energy and experience than the 0<sup>th</sup> opponent so you win.
  Your energy becomes 11 - 1 = 10, and your experience becomes 5 + 2 = 7.
- You have more energy and experience than the 1<sup>st</sup> opponent so you win.
  Your energy becomes 10 - 4 = 6, and your experience becomes 7 + 6 = 13.
- You have more energy and experience than the 2<sup>nd</sup> opponent so you win.
  Your energy becomes 6 - 3 = 3, and your experience becomes 13 + 3 = 16.
- You have more energy and experience than the 3<sup>rd</sup> opponent so you win.
  Your energy becomes 3 - 2 = 1, and your experience becomes 16 + 1 = 17.
You did a total of 6 + 2 = 8 hours of training before the competition, so we return 8.
It can be proven that no smaller answer exists.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> initialEnergy = 2, initialExperience = 4, energy = [1], experience = [3]
<strong>Output:</strong> 0
<strong>Explanation:</strong> You do not need any additional energy or experience to win the competition, so we return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == energy.length == experience.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= initialEnergy, initialExperience, energy[i], experience[i] &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minNumberOfHours(
        self,
        initialEnergy: int,
        initialExperience: int,
        energy: List[int],
        experience: List[int],
    ) -> int:
        ans = 0
        for a, b in zip(energy, experience):
            if initialEnergy <= a:
                ans += a - initialEnergy + 1
                initialEnergy = a + 1
            if initialExperience <= b:
                ans += b - initialExperience + 1
                initialExperience = b + 1
            initialEnergy -= a
            initialExperience += b
        return ans
```

### **Java**

```java
class Solution {
    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int ans = 0;
        for (int i = 0; i < energy.length; ++i) {
            int a = energy[i], b = experience[i];
            if (initialEnergy <= a) {
                ans += a - initialEnergy + 1;
                initialEnergy = a + 1;
            }
            if (initialExperience <= b) {
                ans += b - initialExperience + 1;
                initialExperience = b + 1;
            }
            initialEnergy -= a;
            initialExperience += b;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minNumberOfHours(int initialEnergy, int initialExperience, vector<int>& energy, vector<int>& experience) {
        int ans = 0;
        for (int i = 0; i < energy.size(); ++i) {
            int a = energy[i], b = experience[i];
            if (initialEnergy <= a) {
                ans += a - initialEnergy + 1;
                initialEnergy = a + 1;
            }
            if (initialExperience <= b) {
                ans += b - initialExperience + 1;
                initialExperience = b + 1;
            }
            initialEnergy -= a;
            initialExperience += b;
        }
        return ans;
    }
};
```

### **Go**

```go
func minNumberOfHours(initialEnergy int, initialExperience int, energy []int, experience []int) int {
	ans := 0
	for i, a := range energy {
		b := experience[i]
		if initialEnergy <= a {
			ans += a - initialEnergy + 1
			initialEnergy = a + 1
		}
		if initialExperience <= b {
			ans += b - initialExperience + 1
			initialExperience = b + 1
		}
		initialEnergy -= a
		initialExperience += b
	}
	return ans
}
```

### **TypeScript**

```ts
function minNumberOfHours(
    initialEnergy: number,
    initialExperience: number,
    energy: number[],
    experience: number[],
): number {
    const n = energy.length;
    let ans = 0;
    for (let i = 0; i < n; i++) {
        const minEnergy = energy[i];
        const minExperience = experience[i];
        if (initialEnergy <= minEnergy) {
            const need = minEnergy - initialEnergy + 1;
            ans += need;
            initialEnergy += need;
        }
        if (initialExperience <= minExperience) {
            const need = minExperience - initialExperience + 1;
            ans += need;
            initialExperience += need;
        }
        initialEnergy -= minEnergy;
        initialExperience += minExperience;
    }
    return ans;
}
```

```ts
function minNumberOfHours(
    initialEnergy: number,
    initialExperience: number,
    energy: number[],
    experience: number[],
): number {
    let res = 0;
    for (const v of experience) {
        if (initialExperience <= v) {
            res += v - initialExperience + 1;
            initialExperience = v + 1;
        }
        initialExperience += v;
    }
    for (const v of energy) {
        if (initialEnergy <= v) {
            res += v - initialEnergy + 1;
            initialEnergy = v + 1;
        }
        initialEnergy -= v;
    }
    return res;
}
```

### **C**

```c
int minNumberOfHours(int initialEnergy, int initialExperience, int* energy, int energySize, int* experience, int experienceSize) {
    int res = 0;
    for (int i = 0; i < energySize; i++) {
        if (initialEnergy <= energy[i]) {
            res += energy[i] - initialEnergy + 1;
            initialEnergy = energy[i] + 1;
        }
        if (initialExperience <= experience[i]) {
            res += experience[i] - initialExperience + 1;
            initialExperience = experience[i] + 1;
        }
        initialEnergy -= energy[i];
        initialExperience += experience[i];
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn min_number_of_hours(
        mut initial_energy: i32,
        mut initial_experience: i32,
        energy: Vec<i32>,
        experience: Vec<i32>,
    ) -> i32 {
        let n = energy.len();
        let mut res = 0;
        for i in 0..n {
            if initial_energy <= energy[i] {
                res += energy[i] - initial_energy + 1;
                initial_energy = energy[i] + 1;
            }
            if initial_experience <= experience[i] {
                res += experience[i] - initial_experience + 1;
                initial_experience = experience[i] + 1;
            }
            initial_energy -= energy[i];
            initial_experience += experience[i];
        }
        res
    }
}
```

### **...**

```


```

<!-- tabs:end -->
