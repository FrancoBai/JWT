package com.example.demo.models;

import javax.persistence.*;

//整合SpringData JPA
//编写一个实体类（bean）和数据表进行映射，并且配置好映射关系；

//使用JPA注解配置映射关系
@Entity // 告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "roles") // @Table来指定和哪个数据表对应;如果省略默认表名就是user
public class Role {
	@Id // 这是一个主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增主键
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20) // 这是和数据表对应的一个列
	private ERole name;

	public Role() {

	}

	public Role(ERole name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}

}
