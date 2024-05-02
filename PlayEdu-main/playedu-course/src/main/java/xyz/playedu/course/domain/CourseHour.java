/*
 * Copyright (C) 2023 杭州白书科技有限公司
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package xyz.playedu.course.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName course_hour
 */
@TableName(value = "course_hour")
@Data
public class CourseHour implements Serializable {
    /** */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 课程ID */
    @JsonProperty("course_id")
    private Integer courseId;

    /** 章节ID */
    @JsonProperty("chapter_id")
    private Integer chapterId;

    /** 升序 */
    private Integer sort;

    /** 课时名 */
    private String title;

    /** 课时类型 */
    private String type;

    /** 资源id */
    private Integer rid;

    /** 时长[s] */
    private Integer duration;

    /** */
    @JsonIgnore private Date createdAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CourseHour other = (CourseHour) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCourseId() == null
                        ? other.getCourseId() == null
                        : this.getCourseId().equals(other.getCourseId()))
                && (this.getChapterId() == null
                        ? other.getChapterId() == null
                        : this.getChapterId().equals(other.getChapterId()))
                && (this.getSort() == null
                        ? other.getSort() == null
                        : this.getSort().equals(other.getSort()))
                && (this.getTitle() == null
                        ? other.getTitle() == null
                        : this.getTitle().equals(other.getTitle()))
                && (this.getType() == null
                        ? other.getType() == null
                        : this.getType().equals(other.getType()))
                && (this.getRid() == null
                        ? other.getRid() == null
                        : this.getRid().equals(other.getRid()))
                && (this.getDuration() == null
                        ? other.getDuration() == null
                        : this.getDuration().equals(other.getDuration()))
                && (this.getCreatedAt() == null
                        ? other.getCreatedAt() == null
                        : this.getCreatedAt().equals(other.getCreatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());
        result = prime * result + ((getChapterId() == null) ? 0 : getChapterId().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getRid() == null) ? 0 : getRid().hashCode());
        result = prime * result + ((getDuration() == null) ? 0 : getDuration().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", courseId=").append(courseId);
        sb.append(", chapterId=").append(chapterId);
        sb.append(", sort=").append(sort);
        sb.append(", title=").append(title);
        sb.append(", type=").append(type);
        sb.append(", rid=").append(rid);
        sb.append(", duration=").append(duration);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
