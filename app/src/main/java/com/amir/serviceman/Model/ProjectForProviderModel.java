package com.amir.serviceman.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProjectForProviderModel implements Serializable {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public class Datum {

        @SerializedName("p_id")
        @Expose
        private Integer pId;
        @SerializedName("u_id")
        @Expose
        private Integer uId;
        @SerializedName("project_name")
        @Expose
        private String projectName;
        @SerializedName("business_type")
        @Expose
        private Integer businessType;
        @SerializedName("job_type")
        @Expose
        private Integer jobType;
        @SerializedName("employee_type")
        @Expose
        private Integer employeeType;
        @SerializedName("work_start_time")
        @Expose
        private String workStartTime;
        @SerializedName("work_end_time")
        @Expose
        private String workEndTime;
        @SerializedName("immediate_start")
        @Expose
        private Integer immediateStart;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("location")
        @Expose
        private String location;
        @SerializedName("lat")
        @Expose
        private String lat;
        @SerializedName("lng")
        @Expose
        private String lng;
        @SerializedName("days")
        @Expose
        private Object days;
        @SerializedName("date")
        @Expose
        private Object date;
        @SerializedName("salary_hourly")
        @Expose
        private String salaryHourly;
        @SerializedName("salary_monthly")
        @Expose
        private String salaryMonthly;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("images")
        @Expose
        private List<String> images = null;
        @SerializedName("distance")
        @Expose
        private String distance;
        @SerializedName("totalbids")
        @Expose
        private String totalbids;

        public Integer getPId() {
            return pId;
        }

        public void setPId(Integer pId) {
            this.pId = pId;
        }

        public Integer getUId() {
            return uId;
        }

        public void setUId(Integer uId) {
            this.uId = uId;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public Integer getBusinessType() {
            return businessType;
        }

        public void setBusinessType(Integer businessType) {
            this.businessType = businessType;
        }

        public Integer getJobType() {
            return jobType;
        }

        public void setJobType(Integer jobType) {
            this.jobType = jobType;
        }

        public Integer getEmployeeType() {
            return employeeType;
        }

        public void setEmployeeType(Integer employeeType) {
            this.employeeType = employeeType;
        }

        public String getWorkStartTime() {
            return workStartTime;
        }

        public void setWorkStartTime(String workStartTime) {
            this.workStartTime = workStartTime;
        }

        public String getWorkEndTime() {
            return workEndTime;
        }

        public void setWorkEndTime(String workEndTime) {
            this.workEndTime = workEndTime;
        }

        public Integer getImmediateStart() {
            return immediateStart;
        }

        public void setImmediateStart(Integer immediateStart) {
            this.immediateStart = immediateStart;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public Object getDays() {
            return days;
        }

        public void setDays(Object days) {
            this.days = days;
        }

        public Object getDate() {
            return date;
        }

        public void setDate(Object date) {
            this.date = date;
        }

        public String getSalaryHourly() {
            return salaryHourly;
        }

        public void setSalaryHourly(String salaryHourly) {
            this.salaryHourly = salaryHourly;
        }

        public String getSalaryMonthly() {
            return salaryMonthly;
        }

        public void setSalaryMonthly(String salaryMonthly) {
            this.salaryMonthly = salaryMonthly;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getTotalbids() {
            return totalbids;
        }

        public void setTotalbids(String totalbids) {
            this.totalbids = totalbids;
        }

    }
}
