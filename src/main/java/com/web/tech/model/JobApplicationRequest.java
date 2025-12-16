package com.web.tech.model;


public class JobApplicationRequest {
    private Long jobseekerId;
    private Long postId;
	public synchronized Long getJobseekerId() {
		return jobseekerId;
	}
	public synchronized void setJobseekerId(Long jobseekerId) {
		this.jobseekerId = jobseekerId;
	}
	public synchronized Long getPostId() {
		return postId;
	}
	public synchronized void setPostId(Long postId) {
		this.postId = postId;
	}
	@Override
	public String toString() {
		return "JobApplicationRequest [jobseekerId=" + jobseekerId + ", postId=" + postId + "]";
	}

}
