#pragma once
#include "GameObject.h"

class Player : public GameObject //���ӿ�����Ʈ�� ��ӹ���
{
public:
	Player(COORD startPos);
	~Player();

	GameObject* shoot(); //�Ѿ� �����ؼ� �Ѱ���
	void move(COORD _pos);
	void tick();
};

