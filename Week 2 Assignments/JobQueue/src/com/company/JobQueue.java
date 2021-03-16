//package com.company;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private class Worker {
        public Worker(int idx){
            this.i = idx;
            finishTime = 0;
        }
        int i;
        long finishTime;
    }

//    private void assignJobs() {
//        // TODO: replace this code with a faster algorithm.
//        assignedWorker = new int[jobs.length];
//        startTime = new long[jobs.length];
//        long[] nextFreeTime = new long[numWorkers];
//        for (int i = 0; i < jobs.length; i++) {
//            int duration = jobs[i];
//            int bestWorker = 0;
//            for (int j = 0; j < numWorkers; ++j) {
//                if (nextFreeTime[j] < nextFreeTime[bestWorker])
//                    bestWorker = j;
//            }
//            assignedWorker[i] = bestWorker;
//            startTime[i] = nextFreeTime[bestWorker];
//            nextFreeTime[bestWorker] += duration;
//        }
//    }

    private void assignJobsFaster(){
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        PriorityQueue<Worker> pq = new PriorityQueue<Worker>(numWorkers,
                new Comparator<Worker>() {
                    @Override
                    public int compare(Worker o1, Worker o2) {
                        if(o1.finishTime == o2.finishTime)
                            return o1.i - o2.i;
                        else return (int) (o1.finishTime - o2.finishTime);
                    }
                });
        for(int i = 0; i < numWorkers; i++){
            Worker w = new Worker(i);
            pq.offer(w);
        }
        for (int j = 0; j < jobs.length; j++){
            Worker freeWorker = pq.poll();
            assignedWorker[j] = freeWorker.i;
            startTime[j] = freeWorker.finishTime;
            freeWorker.finishTime += jobs[j];
            pq.offer(freeWorker);
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobsFaster();
        writeResponse();
        out.close();
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
