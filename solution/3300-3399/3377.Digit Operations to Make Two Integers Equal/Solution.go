package main

import (
	"container/heap"
	"strconv"
)

type MinHeap [][]int

func (h MinHeap) Len() int            { return len(h) }
func (h MinHeap) Less(i, j int) bool { return h[i][0] < h[j][0] }
func (h MinHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *MinHeap) Push(x interface{}) {
	*h = append(*h, x.([]int))
}
func (h *MinHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

var sieve []bool

func runSieve() {
	sieve = make([]bool, 100000)
	for i := range sieve {
		sieve[i] = true
	}
	sieve[0], sieve[1] = false, false
	for i := 2; i < 100000; i++ {
		if sieve[i] {
			for j := 2 * i; j < 100000; j += i {
				sieve[j] = false
			}
		}
	}
}

func solve(n int, m int) int {
	pq := &MinHeap{}
	heap.Init(pq)
	heap.Push(pq, []int{n, n})
	visited := make(map[int]bool)

	for pq.Len() > 0 {
		top := heap.Pop(pq).([]int)
		sum, cur := top[0], top[1]

		if visited[cur] {
			continue
		}
		visited[cur] = true

		if cur == m {
			return sum
		}

		s := []rune(strconv.Itoa(cur))
		for i := 0; i < len(s); i++ {
			c := s[i]

			if s[i] < '9' {
				s[i]++
				next, _ := strconv.Atoi(string(s))
				if !sieve[next] && !visited[next] {
					heap.Push(pq, []int{sum + next, next})
				}
				s[i] = c
			}

			if s[i] > '0' && !(i == 0 && s[i] == '1') {
				s[i]--
				next, _ := strconv.Atoi(string(s))
				if !sieve[next] && !visited[next] {
					heap.Push(pq, []int{sum + next, next})
				}
				s[i] = c
			}
		}
	}

	return -1
}

func minOperations(n int, m int) int {
	runSieve()
	if sieve[n] || sieve[m] {
		return -1
	}
	return solve(n, m)
}
