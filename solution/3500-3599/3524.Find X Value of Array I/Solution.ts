function resultArray(nums: number[], k: number): number[] {
    const n = nums.length;
    const result = new Array(k).fill(0);
    
    // f[r] = count of subarrays ending at previous position with product ≡ r (mod k)
    let f = new Array(k).fill(0);
    
    for (let i = 0; i < n; i++) {
        const m = nums[i] % k;
        const newF = new Array(k).fill(0);
        
        // Extend all existing subarrays by multiplying with nums[i]
        for (let r = 0; r < k; r++) {
            const nr = (r * m) % k;
            newF[nr] += f[r];
        }
        
        // Start a new subarray containing just nums[i]
        newF[m] += 1;
        
        // Add all subarrays ending at i to the result
        for (let x = 0; x < k; x++) {
            result[x] += newF[x];
        }
        
        f = newF;
    }
    
    return result;
}