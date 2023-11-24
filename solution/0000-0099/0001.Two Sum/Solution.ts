function twoSum(nums: number[], target: number): number[] {
    // Create a map to store the remaining value needed to reach the target
    const map: Map<number, number> = new Map();

    // Iterate through the array
    for (let i = 0; i < nums.length; i++) {
        // Calculate the remaining value needed to reach the target
        const rem = target - nums[i];

        // Check if the remaining value is already in the map
        if (map.has(rem)) {
            // If found, return the indices of the two numbers
            return [i, map.get(rem)!];
        }

        // Store the current number and its index in the map
        map.set(nums[i], i);
    }

    // If no such pair is found, return an empty array
    return [];
}
