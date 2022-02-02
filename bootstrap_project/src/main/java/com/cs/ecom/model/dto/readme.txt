This folder will contain dto mapping for table

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "wms_applicant")
public class ApplicantDto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "applicant_id")
	private Long applicantId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "apply_date")
	private Date applyDate;
	
	@Column(name = "designation")
	private String designation;
	
	@ManyToOne
	@JoinColumn(name = "phase_updated_by")
	private ApplicantDto phaseUpdatedBy;
	
	@Transient
	private Double basicSalary;
	
	//getter Setter
	//constructor
	//toString
	
	
}