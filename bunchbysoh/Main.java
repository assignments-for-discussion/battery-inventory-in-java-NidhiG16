package bunchbysoh;

public class Main {
  static class CountsBySoH {
    public int healthy = 0;
    public int exchange = 0;
    public int failed = 0;
  };

  static CountsBySoH countBatteriesByHealth(int[] presentCapacities) {
    CountsBySoH counts = new CountsBySoH();
    int ratedCapacity=120;
    for(int i:presentCapacities){
      float soh=100*i/ratedCapacity;
      if(soh>=83 && soh<=100){
        counts.healthy++;
      }else if(soh<83 && soh>63){
        counts.exchange++;
      }else{
        counts.failed++;
      }
    }
    return counts;
  }

  static void testBucketingByHealth() {
    System.out.println("Counting batteries by SoH...\n");
    int[] presentCapacities = {113, 116, 80, 95, 92, 70};
    CountsBySoH counts = countBatteriesByHealth(presentCapacities);
    assert(counts.healthy == 2);
    assert(counts.exchange == 3);
    assert(counts.failed == 1);
    System.out.println("Healthy batteries: "+counts.healthy);
    System.out.println("Batteries to exchange: "+counts.exchange);
    System.out.println("Failed batteries: "+counts.failed);
    int[] edgeTestCapacities = {100, 120, 75}; //to test for egde cases (100,83,62)
    assert(counts.healthy == 2);
    assert(counts.exchange == 0);
    assert(counts.failed == 1);
    CountsBySoH edgeCounts = countBatteriesByHealth(edgeTestCapacities);
    System.out.println("Healthy batteries: "+edgeCounts.healthy);
    System.out.println("Batteries to exchange: "+edgeCounts.exchange);
    System.out.println("Failed batteries: "+edgeCounts.failed);
    System.out.println("Done counting :)\n");
  }

  public static void main(String[] args) {
    testBucketingByHealth();
  }
}