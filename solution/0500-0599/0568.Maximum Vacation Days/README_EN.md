# [568. Maximum Vacation Days](https://leetcode.com/problems/maximum-vacation-days)

[中文文档](/solution/0500-0599/0568.Maximum%20Vacation%20Days/README.md)

## Description

<p>

LeetCode wants to give one of its best employees the option to travel among <b>N</b> cities to collect algorithm problems. But all work and no play makes Jack a dull boy, you could take vacations in some particular cities and weeks. Your job is to schedule the traveling to maximize the number of vacation days you could take, but there are certain rules and restrictions you need to follow.

</p>

<p><b>Rules and restrictions:</b><br>

<ol>

<li>You can only travel among <b>N</b> cities, represented by indexes from 0 to N-1. Initially, you are in the city indexed 0 on <b>Monday</b>.</li>

<li>The cities are connected by flights. The flights are represented as a <b>N*N</b> matrix (not necessary symmetrical), called <b>flights</b> representing the airline status from the city i to the city j. If there is no flight from the city i to the city j, <b>flights[i][j] = 0</b>; Otherwise, <b>flights[i][j] = 1</b>. Also, <b>flights[i][i] = 0</b> for all i.</li>

<li>You totally have <b>K</b> weeks (<b>each week has 7 days</b>) to travel. You can only take flights at most once <b>per day</b> and can only take flights on each week's <b>Monday</b> morning. Since flight time is so short, we don't consider the impact of flight time.</li>

<li>For each city, you can only have restricted vacation days in different weeks, given an <b>N*K</b> matrix called <b>days</b> representing this relationship. For the value of <b>days[i][j]</b>, it represents the maximum days you could take vacation in the city <b>i</b> in the week <b>j</b>.</li>

</ol>

</p>

<p>You're given the <b>flights</b> matrix and <b>days</b> matrix, and you need to output the maximum vacation days you could take during <b>K</b> weeks.</p>

<p><b>Example 1:</b><br />

<pre>

<b>Input:</b>flights = [[0,1,1],[1,0,1],[1,1,0]], days = [[1,3,1],[6,0,3],[3,3,3]]

<b>Output:</b> 12

<b>Explanation:</b> <br>Ans = 6 + 3 + 3 = 12. <br>

One of the best strategies is:

1st week : fly from city 0 to city 1 on Monday, and play 6 days and work 1 day. <br/>(Although you start at city 0, we could also fly to and start at other cities since it is Monday.) 

2nd week : fly from city 1 to city 2 on Monday, and play 3 days and work 4 days.

3rd week : stay at city 2, and play 3 days and work 4 days.

</pre>

</p>

<p><b>Example 2:</b><br />

<pre>

<b>Input:</b>flights = [[0,0,0],[0,0,0],[0,0,0]], days = [[1,1,1],[7,7,7],[7,7,7]]

<b>Output:</b> 3

<b>Explanation:</b> <br>Ans = 1 + 1 + 1 = 3. <br>

Since there is no flights enable you to move to another city, you have to stay at city 0 for the whole 3 weeks. <br/>For each week, you only have one day to play and six days to work. <br/>So the maximum number of vacation days is 3.

</pre>

</p>

<p><b>Example 3:</b><br />

<pre>

<b>Input:</b>flights = [[0,1,1],[1,0,1],[1,1,0]], days = [[7,0,0],[0,7,0],[0,0,7]]

<b>Output:</b> 21

<b>Explanation:</b><br>Ans = 7 + 7 + 7 = 21<br>

One of the best strategies is:

1st week : stay at city 0, and play 7 days. 

2nd week : fly from city 0 to city 1 on Monday, and play 7 days.

3rd week : fly from city 1 to city 2 on Monday, and play 7 days.

</pre>

</p>

<p><b>Note:</b><br>

<ol>

<li><b>N and K</b> are positive integers, which are in the range of [1, 100].</li>

<li>In the matrix <b>flights</b>, all the values are integers in the range of [0, 1].</li>

<li>In the matrix <b>days</b>, all the values are integers in the range [0, 7].</li>

<li>You could stay at a city beyond the number of vacation days, but you should <b>work</b> on the extra days, which won't be counted as vacation days.</li>

<li>If you fly from the city A to the city B and take the vacation on that day, the deduction towards vacation days will count towards the vacation days of city B in that week.</li>

<li>We don't consider the impact of flight hours towards the calculation of vacation days.</li>

</ol>

</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
