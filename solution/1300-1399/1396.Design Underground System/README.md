# [1396. 设计地铁系统](https://leetcode.cn/problems/design-underground-system)

[English Version](/solution/1300-1399/1396.Design%20Underground%20System/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>地铁系统跟踪不同车站之间的乘客出行时间，并使用这一数据来计算从一站到另一站的平均时间。</p>

<p>实现 <code>UndergroundSystem</code> 类：</p>

<ul>
	<li><code>void checkIn(int id, string stationName, int t)</code>
    <ul>
    	<li>通行卡 ID 等于 <code>id</code> 的乘客，在时间 <code>t</code> ，从 <code>stationName</code> 站进入</li>
    	<li>乘客一次只能从一个站进入</li>
    </ul>
    </li>
    <li><code>void checkOut(int id, string stationName, int t)</code>
    <ul>
    	<li>通行卡 ID 等于 <code>id</code> 的乘客，在时间 <code>t</code> ，从 <code>stationName</code> 站离开</li>
    </ul>
    </li>
    <li><code>double getAverageTime(string startStation, string endStation)</code>
    <ul>
    	<li>返回从 <code>startStation</code> 站到 <code>endStation</code> 站的平均时间</li>
    	<li>平均时间会根据截至目前所有从 <code>startStation</code> 站 <strong>直接</strong> 到达 <code>endStation</code> 站的行程进行计算，也就是从 <code>startStation</code> 站进入并从 <code>endStation</code> 离开的行程</li>
    	<li>从 <code>startStation</code> 到 <code>endStation</code> 的行程时间与从 <code>endStation</code> 到 <code>startStation</code> 的行程时间可能不同</li>
    	<li>在调用 <code>getAverageTime</code> 之前，至少有一名乘客从 <code>startStation</code> 站到达 <code>endStation</code> 站</li>
    </ul>
    </li>
</ul>

<p>你可以假设对 <code>checkIn</code> 和 <code>checkOut</code> 方法的所有调用都是符合逻辑的。如果一名乘客在时间 <code>t<sub>1</sub></code> 进站、时间 <code>t<sub>2</sub></code> 出站，那么 <code>t<sub>1</sub> &lt; t<sub>2</sub></code> 。所有时间都按时间顺序发生。</p>
&nbsp;

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入</strong>
["UndergroundSystem","checkIn","checkIn","checkIn","checkOut","checkOut","checkOut","getAverageTime","getAverageTime","checkIn","getAverageTime","checkOut","getAverageTime"]
[[],[45,"Leyton",3],[32,"Paradise",8],[27,"Leyton",10],[45,"Waterloo",15],[27,"Waterloo",20],[32,"Cambridge",22],["Paradise","Cambridge"],["Leyton","Waterloo"],[10,"Leyton",24],["Leyton","Waterloo"],[10,"Waterloo",38],["Leyton","Waterloo"]]

<strong>输出</strong>
[null,null,null,null,null,null,null,14.00000,11.00000,null,11.00000,null,12.00000]

<strong>解释</strong>
UndergroundSystem undergroundSystem = new UndergroundSystem();
undergroundSystem.checkIn(45, "Leyton", 3);
undergroundSystem.checkIn(32, "Paradise", 8);
undergroundSystem.checkIn(27, "Leyton", 10);
undergroundSystem.checkOut(45, "Waterloo", 15);  // 乘客 45 "Leyton" -&gt; "Waterloo" ，用时 15-3 = 12
undergroundSystem.checkOut(27, "Waterloo", 20);  // 乘客 27 "Leyton" -&gt; "Waterloo" ，用时 20-10 = 10
undergroundSystem.checkOut(32, "Cambridge", 22); // 乘客 32 "Paradise" -&gt; "Cambridge" ，用时 22-8 = 14
undergroundSystem.getAverageTime("Paradise", "Cambridge"); // 返回 14.00000 。只有一个 "Paradise" -&gt; "Cambridge" 的行程，(14) / 1 = 14
undergroundSystem.getAverageTime("Leyton", "Waterloo");    // 返回 11.00000 。有两个 "Leyton" -&gt; "Waterloo" 的行程，(10 + 12) / 2 = 11
undergroundSystem.checkIn(10, "Leyton", 24);
undergroundSystem.getAverageTime("Leyton", "Waterloo");    // 返回 11.00000
undergroundSystem.checkOut(10, "Waterloo", 38);  // 乘客 10 "Leyton" -&gt; "Waterloo" ，用时 38-24 = 14
undergroundSystem.getAverageTime("Leyton", "Waterloo");    // 返回 12.00000 。有三个 "Leyton" -&gt; "Waterloo" 的行程，(10 + 12 + 14) / 3 = 12
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入</strong>
["UndergroundSystem","checkIn","checkOut","getAverageTime","checkIn","checkOut","getAverageTime","checkIn","checkOut","getAverageTime"]
[[],[10,"Leyton",3],[10,"Paradise",8],["Leyton","Paradise"],[5,"Leyton",10],[5,"Paradise",16],["Leyton","Paradise"],[2,"Leyton",21],[2,"Paradise",30],["Leyton","Paradise"]]

<strong>输出</strong>
[null,null,null,5.00000,null,null,5.50000,null,null,6.66667]

<strong>解释</strong>
UndergroundSystem undergroundSystem = new UndergroundSystem();
undergroundSystem.checkIn(10, "Leyton", 3);
undergroundSystem.checkOut(10, "Paradise", 8); // 乘客 10 "Leyton" -&gt; "Paradise" ，用时 8-3 = 5
undergroundSystem.getAverageTime("Leyton", "Paradise"); // 返回 5.00000 ，(5) / 1 = 5
undergroundSystem.checkIn(5, "Leyton", 10);
undergroundSystem.checkOut(5, "Paradise", 16); // 乘客 5 "Leyton" -&gt; "Paradise" ，用时 16-10 = 6
undergroundSystem.getAverageTime("Leyton", "Paradise"); // 返回 5.50000 ，(5 + 6) / 2 = 5.5
undergroundSystem.checkIn(2, "Leyton", 21);
undergroundSystem.checkOut(2, "Paradise", 30); // 乘客 2 "Leyton" -&gt; "Paradise" ，用时 30-21 = 9
undergroundSystem.getAverageTime("Leyton", "Paradise"); // 返回 6.66667 ，(5 + 6 + 9) / 3 = 6.66667
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= id, t &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= stationName.length, startStation.length, endStation.length &lt;= 10</code> 次</li>
	<li>所有字符串由大小写英文字母与数字组成</li>
	<li>总共最多调用 <code>checkIn</code>、<code>checkOut</code> 和 <code>getAverageTime</code> 方法 <code>2 * 10<sup>4 </sup></code></li>
	<li>与标准答案误差在 <code>10<sup>-5</sup></code> 以内的结果都被视为正确结果</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“哈希表”实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class UndergroundSystem:
    def __init__(self):
        self.check_in_station = {}
        self.check_in_time = {}
        self.total_time = {}

    def checkIn(self, id: int, stationName: str, t: int) -> None:
        self.check_in_station[id] = stationName
        self.check_in_time[id] = t

    def checkOut(self, id: int, stationName: str, t: int) -> None:
        cost = t - self.check_in_time.pop(id)
        start_station = self.check_in_station.pop(id)
        stations = start_station + '.' + stationName
        times = self.total_time.get(stations, [0, 0])
        times[0] += cost
        times[1] += 1
        self.total_time[stations] = times

    def getAverageTime(self, startStation: str, endStation: str) -> float:
        stations = startStation + '.' + endStation
        times = self.total_time[stations]
        return times[0] / times[1]


# Your UndergroundSystem object will be instantiated and called as such:
# obj = UndergroundSystem()
# obj.checkIn(id,stationName,t)
# obj.checkOut(id,stationName,t)
# param_3 = obj.getAverageTime(startStation,endStation)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class UndergroundSystem {
    private Map<Integer, String> checkInStation;
    private Map<Integer, Integer> checkInTime;
    private Map<String, int[]> totalTime;

    public UndergroundSystem() {
        checkInStation = new HashMap<>();
        checkInTime = new HashMap<>();
        totalTime = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkInStation.put(id, stationName);
        checkInTime.put(id, t);
    }

    public void checkOut(int id, String stationName, int t) {
        int cost = t - checkInTime.remove(id);
        String startStation = checkInStation.remove(id);
        String stations = startStation + "." + stationName;
        int[] times = totalTime.getOrDefault(stations, new int[2]);
        times[0] += cost;
        ++times[1];
        totalTime.put(stations, times);
    }

    public double getAverageTime(String startStation, String endStation) {
        String stations = startStation + "." + endStation;
        int[] times = totalTime.get(stations);
        return times[0] * 1.0 / times[1];
    }
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */
```

### **...**

```

```

<!-- tabs:end -->
