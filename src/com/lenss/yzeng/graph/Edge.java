package com.lenss.yzeng.graph;

import com.lenss.yzeng.utils.Sortable;

public class Edge implements Sortable{
		private int v;
		private int w;
		private double weight;
		
		public Edge(int v, int w, double weight) {
			// TODO Auto-generated constructor stub
			this.v = v;
			this.w = w;
			this.weight = weight;
		}

		public int getV() {
			return v;
		}

		public void setV(int v) {
			this.v = v;
		}

		public int getW() {
			return w;
		}

		public void setW(int w) {
			this.w = w;
		}

		public double getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		@Override
		public double getKey() {
			// TODO Auto-generated method stub
			return this.weight;
		}
	}
