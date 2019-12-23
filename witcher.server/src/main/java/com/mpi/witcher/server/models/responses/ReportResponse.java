package com.mpi.witcher.server.models.responses;

import java.util.List;

public class ReportResponse {
    private List<NameValuePair> reportData;
    private List<ChartData> charts;

    public ReportResponse(List<NameValuePair> reportData, List<ChartData> charts) {
        this.reportData = reportData;
        this.charts = charts;
    }

    public List<NameValuePair> getReportData() {
        return reportData;
    }

    public void setReportData(List<NameValuePair> reportData) {
        this.reportData = reportData;
    }

    public List<ChartData> getCharts() {
        return charts;
    }

    public void setCharts(List<ChartData> charts) {
        this.charts = charts;
    }

    public static class ChartData {
        private List<String> labels;
        private List<Dataset> datasets;

        public ChartData(List<String> labels, List<Dataset> datasets) {
            this.labels = labels;
            this.datasets = datasets;
        }

        public List<String> getLabels() {
            return labels;
        }

        public void setLabels(List<String> labels) {
            this.labels = labels;
        }

        public List<Dataset> getDatasets() {
            return datasets;
        }

        public void setDatasets(List<Dataset> datasets) {
            this.datasets = datasets;
        }

        public static class Dataset {
            private String label;
            private float[] data;

            public Dataset(String label, float[] data) {
                this.label = label;
                this.data = data;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public float[] getData() {
                return data;
            }

            public void setData(float[] data) {
                this.data = data;
            }
        }
    }

    public static class NameValuePair {
        private String name;
        private Object value;

        public NameValuePair(String name, Object value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }
}
