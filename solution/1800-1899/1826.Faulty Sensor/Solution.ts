function badSensor(sensor1: number[], sensor2: number[]): number {
    let i = 0;
    const n = sensor1.length;
    while (i < n - 1) {
        if (sensor1[i] !== sensor2[i]) {
            break;
        }
        ++i;
    }
    while (i < n - 1) {
        if (sensor1[i + 1] !== sensor2[i]) {
            return 1;
        }
        if (sensor1[i] !== sensor2[i + 1]) {
            return 2;
        }
        ++i;
    }
    return -1;
}
