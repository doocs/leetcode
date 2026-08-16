function elevatorRequests(n: number, requests: number[]): number {
    let ans: number = requests[0];
    for (let i = 1; i < requests.length; ++i) {
        ans += Math.abs(requests[i] - requests[i - 1]);
    }
    return ans;
}
