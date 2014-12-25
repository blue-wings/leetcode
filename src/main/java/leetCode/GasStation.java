package leetCode;

/**
 * User: FR
 * Time: 12/24/14 3:52 PM
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 * Note:
 * The solution is guaranteed to be unique.
 */
public class GasStation {

    /**
     * failed : time limit exceeded
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuitFailed(int[] gas, int[] cost) {
        if(gas == null || gas.length==0){
            return -1;
        }
        for(int i=0; i<gas.length; i++){
            if(cost[i]>gas[i]){
                continue;
            }
            int totalGas =0 ;
            int totalCost =0 ;
            int j=i;
            boolean circuitFlag = true;
            while (true){
                if(j == i && circuitFlag){
                    circuitFlag = false;
                }else if(j==i && !circuitFlag ){
                    return i;
                }
                totalCost += cost[j];
                totalGas += gas[j];
                if(totalCost > totalGas){
                    break;
                }
                if((j+1)==gas.length){
                    j = 0;
                }else {
                    j++;
                }
            }
            continue;
        }
        return -1;
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int gasCostMin = 0;
        int gasMinIndex = -1;
        int totalGas = 0;
        int totalCost = 0;
        for(int i=0; i<gas.length; i++){
            if((gas[i]-cost[i]) < gasCostMin){
                gasCostMin = gas[i]-cost[i];
                gasMinIndex = i;
            }
            totalCost += cost[i];
            totalGas += gas[i];
        }
        if(totalGas >= totalCost){
            if(gasMinIndex == -1){
                return 0;
            }
            int j = gasMinIndex;
            while (true){
                if(gas[j]-cost[j]>0){
                    return j;
                }
                j++;
                if(j==gas.length){
                       j=0;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args){
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        GasStation gasStation = new GasStation();
        System.out.println(gasStation.canCompleteCircuit(gas, cost));
    }
}
